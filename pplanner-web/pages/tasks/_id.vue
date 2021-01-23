<template>
	<div class="h-full flex flex-col">
		<template v-if="menuItem != null">
			<div class="flex flex-row items-baseline gap-2 font-bold px-4">
				<i :class="iconItem" class="text-primary-400 text-sm"></i>
				<div class="text-primary-700 text-lg">{{ menuItem.name }}</div>

				<tw-tab-container>
					<tw-tab-item v-for="view in views" :key="view.id" :active="$route.query.view === view.name" :name="view.name">
						<i :class="view.icon" class="mr-1"></i>
						<span>{{ view.label }}</span>
					</tw-tab-item>
					<div class="h-6 border ml-2"></div>
					<div class="ml-2 cursor-pointer transform duration-200 hover:text-secondary hover:scale-110">
						<i class="fas fa-plus mr-1"></i>
						<span>Vue</span>
					</div>
				</tw-tab-container>
			</div>

			<div class="flex flex-col border-t bg-primary-100 w-full h-full overflow-x-auto overflow-y-auto">
				<component :is="viewComponent" v-model="menuItem"></component>
			</div>
		</template>

		<template v-else>
			<div class="text-primary font-medium">Aucun workspace existant, merci de créer un workspace</div>
		</template>

		<div class="fixed bottom-10 right-10 p-2 rounded-lg bg-secondary text-white font-bold shadow-2xl cursor-pointer" @click="handleCreateTask">
			<i class="fas fa-plus mr-2"></i>
			<span>Tasks</span>
		</div>

		<template v-if="task != null">
			<tw-modal :visible.sync="showModalEditTask" title="Création d'une tâche">
				<tw-input-text label="Nom de la tâche" required></tw-input-text>
				<template #actions>
					<button class="p-btn p-btn--primary">Annuler</button>
					<button class="p-btn p-btn--success">Créer</button>
				</template>
			</tw-modal>
		</template>
	</div>
</template>

<script lang="ts">
import { Component, Vue } from 'nuxt-property-decorator'
import TwTabContainer from "~/components/shared/TwTabContainer.vue";
import TwTabItem from "~/components/shared/TwTabItem.vue";
import { Context } from "@nuxt/types";
import TwInputText from "~/components/shared/TwInputText.vue";
import TwModal from "~/components/shared/TwModal.vue";


@Component({
	components: {
		TwInputText,
		TwTabContainer,
		TwTabItem,
		TwModal,
	},
})
export default class PageTaskIndex extends Vue {

	public menuItem: Models.ProjectMenuItem = null
	public showModalEditTask = false
	public task: Models.Task = null
	public views: Models.TaskViewMenu[] = []

	get iconItem () {
		switch (this.menuItem.type) {
			case 'FOLDER':
				return 'fas fa-folder'
			case 'LIST':
				return 'fas fa-list'
			case 'WORKSPACE':
				return 'fas fa-globe'
		}
	}

	get viewComponent () {
		const viewComponent = this.views.find(view => view.name === this.$route.query.view)
		if (viewComponent != null) {
			return viewComponent.component
		}
	}

	async asyncData (ctx: Context) {
		if (ctx.params.id == null) {
			// Si pas de paramètre, on récupère le premier workspace créé. S'il n'y en a pas, alors on retourne vide
			const firstWorkspace = await ctx.$api.tasks.findFirstWorkspaceByProjectId(ctx.store.state.selectedProject)
			if (firstWorkspace != null) {
				return ctx.next({
					name: 'tasks-id-view',
					params: {
						id: firstWorkspace.id,
					},
					query: {
						view: 'list',
					},
				})
			}

			return {}
		}

		await ctx.store.dispatch('selectMenu', ctx.params.id)

		return {
			menuItem: await ctx.$api.items.findById(ctx.params.id),
			views: [
				{ id: 1, name: 'list', label: 'Liste', icon: 'fas fa-th-list', component: 'app-view-list' },
				{ id: 2, name: 'kanban', label: 'Kanban', icon: 'fab fa-gitter', component: 'app-view-kanban' },
				{ id: 3, name: 'calendar', label: 'Calendrier', icon: 'fas fa-calendar-alt', component: 'app-view-calendar' },
				{ id: 4, name: 'gantt', label: 'Gantt', icon: 'fas fa-stream', component: 'app-view-gantt' },
			],
		}
	}

	beforeRouteUpdate (to, from, next) {
		if (to.query.view == null) {
			next({ name: 'tasks-id', params: { id: to.params.id }, query: { view: 'list' } })
		}
		next()
	}

	created () {
		this.$bus.$on('on-select-view-tab', (event) => {
			this.$router.push({ name: 'tasks-id', params: { id: this.$route.params.id }, query: { view: event.name } })
		})
	}

	handleCreateTask () {
		this.task = {}
		this.showModalEditTask = true
	}
}
</script>

<style>

</style>
