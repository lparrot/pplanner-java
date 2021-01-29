import { Component, Vue } from 'nuxt-property-decorator'
import { VModel } from 'vue-property-decorator'

@Component
export class TasksMixin extends Vue {
	@VModel() item: Models.ProjectMenuItem

	public items: any[] = []
	public total: number = 0

	async created () {
		this.$root.$on('pplanner:update-task-list', async () => {
			await this.fetchData()
		})
	}

	async fetchData () {
		const tasks = await this.$api.tasks.findAllTasksByMenuItemId(this.$store.getters.activeMenu)
		const statusList = await this.$api.task_status.findStatusByItemId(this.item.id)

		this.total = tasks.length

		statusList.forEach(status => {
			this.items.push({
				status,
				tasks: tasks[status.id] ?? []
			})
		})
	}
}
