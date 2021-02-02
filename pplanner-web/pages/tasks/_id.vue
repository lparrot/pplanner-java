<template>
	<div class="h-full flex flex-col">
		<template v-if="menuItem != null">
			<div class="flex flex-row items-baseline gap-2 font-bold px-4 overflow-y-hidden overflow-x-auto">
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
				<div v-if="viewComponent != null" class="h-full">
					<component :is="viewComponent" v-model="menuItem"></component>
				</div>
			</div>
		</template>

		<template v-else>
			<div class="text-primary font-medium px-4">Aucun workspace existant, merci de créer un workspace</div>
		</template>

		<div class="fixed bottom-10 right-10 p-2 rounded-lg bg-secondary text-white font-bold shadow-2xl cursor-pointer" @click="handleShowModalCreateTask">
			<i class="fas fa-plus mr-2"></i>
			<span>Tasks</span>
		</div>

		<validation-observer ref="validator" tag="form" @submit.prevent="handleSubmitCreateTask">
			<template v-if="task != null">
				<tw-modal :visible.sync="showModalEditTask" title="Création d'une tâche">

					<app-task-create v-model="task"></app-task-create>

					<template #actions>
						<button class="p-btn p-btn--primary" type="button" @click="showModalEditTask = false">Annuler</button>
						<button class="p-btn p-btn--success" type="submit">Créer</button>
					</template>
				</tw-modal>
			</template>
		</validation-observer>
	</div>
</template>

<script lang="ts">
import { Action, Component, Getter, Ref, Vue } from 'nuxt-property-decorator'
import TwTabContainer from "~/components/shared/TwTabContainer.vue";
import TwTabItem from "~/components/shared/TwTabItem.vue";
import TwInputText from "~/components/shared/TwInputText.vue";
import TwModal from "~/components/shared/TwModal.vue";
import TwDropdown from "~/components/shared/TwDropdown.vue";
import AppProjectMenuItemContainer from "~/components/app/AppProjectMenuItemContainer.vue";
import AppTaskCreate from "~/components/app/AppTaskCreate.vue";


@Component({
	watchQuery: [ 'view' ],
	components: {
		AppTaskCreate,
		AppProjectMenuItemContainer,
		TwDropdown,
		TwInputText,
		TwModal,
		TwTabContainer,
		TwTabItem,
	},
})
export default class PageTaskIndex extends Vue {

	@Ref('validator') validator: any

	@Action('goToTaskIdListPage') goToTaskIdListPage
	@Action('selectMenu') selectMenu
	@Getter('activeProject') activeProject
	@Getter('activeMenu') activeMenu

	public menuItem: Models.ProjectMenuItem = null
	public showModalEditTask = false
	public task: Models.TaskEdit = null
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

	async fetch () {
		if (this.activeProject == null) {
			return this.$router.push('/tasks');
		}

		if (this.$route.query.view == null) {
			return this.goToTaskIdListPage()
		}

		try {
			if (this.activeMenu != null) {
				await this.$api.items.findByIdAndProjectId(this.activeMenu, this.activeProject)
				await this.selectMenu(this.activeMenu)
			}
		} catch (err) {
			this.selectMenu(null)
			return this.$router.push('/tasks')
		}

		if (this.activeMenu != null) {
			this.menuItem = await this.$api.items.findById(this.activeMenu)
		}

		this.$bus.$on('on-select-view-tab', (event) => {
			this.$router.push({ name: 'tasks-id', params: { id: this.$route.params.id }, query: { view: event.name } })
		})

		this.views = [
			{ id: 1, name: 'list', label: 'Liste', icon: 'fas fa-th-list', component: 'app-view-list' },
			{ id: 2, name: 'kanban', label: 'Kanban', icon: 'fab fa-gitter', component: 'app-view-kanban' },
			{ id: 3, name: 'calendar', label: 'Calendrier', icon: 'fas fa-calendar-alt', component: 'app-view-calendar' },
			{ id: 4, name: 'gantt', label: 'Gantt', icon: 'fas fa-stream', component: 'app-view-gantt' },
		]
	}

	async handleShowModalCreateTask () {
		const item = await this.$api.items.findById(this.activeMenu);
		this.task = {}
		if (item.type === 'LIST') {
			this.$set(this.task, 'item', item)
		}
		this.showModalEditTask = true
		this.validator.reset()
	}

	async handleSubmitCreateTask () {
		const valid = await this.validator.validate()
		if (valid) {
			await this.$api.tasks.createTask(this.task)
			this.showModalEditTask = false
			this.$bus.$emit('pplanner:tasks_update')
		}
	}
}
</script>

<style>

</style>
