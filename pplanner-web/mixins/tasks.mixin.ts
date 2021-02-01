import { Component, Vue } from 'nuxt-property-decorator'
import { VModel } from 'vue-property-decorator'

@Component
export class TasksMixin extends Vue {
	@VModel() item: Models.ProjectMenuItem

	public items: any[] = []
	public total: number = 0
	public statusList: any[]

	async fetchData () {
		this.statusList = await this.$api.task_status.findStatusByItemId(this.item.id)
		this.statusList.unshift({ id: '0', name: 'Sans statut', color: 'bg-warn text-white' })

		this.$bus.$on('pplanner:tasks_update', async () => {
			await this.fetchData()
		})

		this.items = []
		const tasks = await this.$api.tasks.findAllTasksByMenuItemId(this.$store.getters.activeMenu)

		this.total = tasks.length

		this.statusList.forEach(status => {
			this.items.push({
				status,
				tasks: tasks[status.id] ?? []
			})
		})
	}
}
