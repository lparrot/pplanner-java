<template>
	<section>
		<app-project-menu-item v-for="workspace in workspaces" :key="workspace.id" :item="workspace"></app-project-menu-item>
	</section>
</template>

<script lang="ts">
import { Component, Provide, Vue } from "nuxt-property-decorator";
import AppProjectMenuItem from "~/components/app/AppProjectMenuItem.vue";
import { VModel } from "vue-property-decorator";

@Component({
	components: {
		AppProjectMenuItem
	},
})
export default class AppProjectMenuItemContainer extends Vue {
	@Provide('container') container = this
	@VModel() model: Models.ProjectMenuItem

	public workspaces: Models.ProjectMenuItem[] = []

	async created () {
		this.workspaces = await this.$api.projects.findAllWorkspaceByProjectId(this.$store.state.selectedProject)
	}

	setModel (item: Models.ProjectMenuItem) {
		this.model = item
	}
}
</script>
