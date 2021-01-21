<template>
	<div class="h-full flex flex-col">
		<template v-if="task != null">
			<div class="flex flex-row items-baseline gap-2 font-bold px-4">
				<i :class="iconItem" class="text-primary-400 text-sm"></i>
				<div class="text-primary-700 text-lg">{{ task.name }}</div>

				<p-tab-container>
					<p-tab-item v-for="view in views" :key="view.id" :active="$route.query.view === view.name" :name="view.name">
						<i :class="view.icon" class="mr-1"></i>
						<span>{{ view.label }}</span>
					</p-tab-item>
					<div class="h-6 border ml-2"></div>
					<div class="ml-2 cursor-pointer transform duration-200 hover:text-secondary hover:scale-110">
						<i class="fas fa-plus mr-1"></i>
						<span>Vue</span>
					</div>
				</p-tab-container>
			</div>

			<div class="flex flex-col border-t bg-primary-100 w-full h-full overflow-x-auto overflow-y-auto">
				<component :is="viewComponent" v-model="task"></component>
			</div>
		</template>

		<template v-else>
			<div class="text-primary font-medium">Aucun workspace existant, merci de créer un workspace</div>
		</template>

		<div class="fixed bottom-10 right-10 p-2 rounded-lg bg-secondary text-white font-bold shadow-2xl cursor-pointer">
			<i class="fas fa-plus mr-2"></i>
			<span>Tasks</span>
		</div>
	</div>
</template>

<script lang="ts">
import { Component, Vue } from 'nuxt-property-decorator'
import PTabContainer from "~/components/PTabContainer.vue";
import PTabItem from "~/components/PTabItem.vue";
import { Context } from "@nuxt/types";


@Component({
	components: {
		PTabContainer,
		PTabItem,
	},
})
export default class PageTaskIndex extends Vue {

	public task: Models.ProjectMenuItem = null
	public views: Models.TaskViewMenu[] = []

	get iconItem () {
		switch (this.task.type) {
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
			const firstWorkspace = ctx.$api.tasks.findFirstWorkspaceByProjectId(ctx.store.state.selectedProject)
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

		await ctx.store.dispatch('selectProjectItem', parseInt(ctx.params.id))

		return {
			task: ctx.$api.items.findById(ctx.params.id),
			views: [
				{ id: 1, name: 'list', label: 'Liste', icon: 'fas fa-th-list', component: 'p-view-list' },
				{ id: 2, name: 'kanban', label: 'Kanban', icon: 'fab fa-gitter', component: 'p-view-kanban' },
				{ id: 3, name: 'calendar', label: 'Calendrier', icon: 'fas fa-calendar-alt', component: 'p-view-calendar' },
				{ id: 4, name: 'gantt', label: 'Gantt', icon: 'fas fa-stream', component: 'p-view-gantt' },
			],
		}
	}

	beforeRouteUpdate (to, from, next) {
		if (to.query.view == null) {
			next({ name: 'tasks-id', params: { id: this.$route.params.id }, query: { view: 'list' } })
		}
		next()
	}

	created () {
		this.$bus.$on('on-select-view-tab', (event) => {
			this.$router.push({ name: 'tasks-id', params: { id: this.$route.params.id }, query: { view: event.name } })
		})
	}
}
</script>

<style>

</style>
