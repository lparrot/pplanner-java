<template>
	<section>
		<app-project-menu-item v-for="workspace in workspaces" :key="workspace.id" :item="workspace"></app-project-menu-item>
	</section>
</template>

<script lang="ts">
import {Action, Component, Getter, Prop, Provide, Vue, Watch} from "nuxt-property-decorator";
import AppProjectMenuItem from "~/components/app/AppProjectMenuItem.vue";
import {VModel} from "vue-property-decorator";

@Component({
	components: {
		AppProjectMenuItem
	},
})
export default class AppProjectMenuItemContainer extends Vue {
	@Provide('container') container = this

	@Prop({type: Boolean}) readonly editable !: boolean
	@Prop({type: Boolean}) readonly allOpened !: boolean
	@Prop({type: Array, default: () => ['WORKSPACE', 'FOLDER', 'LIST']}) readonly selectableTypes !: string[]

	@VModel() model: Models.ProjectMenuItem

	@Action('goToTaskIdListPage') goToTaskIdListPage
	@Action('selectMenu') selectMenu
	@Getter('activeProject') activeProject
	@Getter('activeMenu') activeMenu

	public workspaces: Models.ProjectMenuItem[] = []

	async fetch() {
		this.$bus.$emit('pplanner:items_update')
	}

	async created() {
		this.$bus.$once('pplanner:items_update', async () => {
			this.workspaces = await this.$api.projects.findAllWorkspaceByProjectId(this.activeProject)
			if (this.activeMenu == null && this.workspaces.length > 0) {
				await this.selectMenu(this.workspaces[0].id)
				await this.goToTaskIdListPage()
			}
		})
	}

	setModel(item: Models.ProjectMenuItem) {
		this.model = item
	}

	@Watch('activeProject')
	public onChangeActiveProject(val, old) {
		this.$bus.$emit('pplanner:items_update')
	}

	beforeDestroyed() {
		this.$bus.$off('pplanner:items_update')
	}
}
</script>
