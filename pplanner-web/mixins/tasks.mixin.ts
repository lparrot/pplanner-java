import { Action, Component, State, Vue } from 'nuxt-property-decorator'
import { VModel } from 'vue-property-decorator'

@Component
export class TasksMixin extends Vue {
	@VModel() item: Models.ProjectMenuItem

	@State(state => state['task-view'].statusList) statusList!: any[]
	@Action('task-view/findStatusListByItemId') findStatusListByItemId

	public items: any[] = []
	public total: number = 0

	async fetchData () {
		await this.findStatusListByItemId(this.item.id)

		this.$bus.$on('pplanner:tasks_update', async () => {
			await this.fetchData()
		})

		this.items = []
		const tasks = await this.$api.tasks.findAllTasksByMenuItemId(this.$store.getters.activeMenu)

		// TODO : Récupérer le bon total
		this.total = tasks.length

		this.statusList.forEach(status => {
			this.items.push({
				status,
				tasks: tasks[status.id] ?? [],
			})
		})
	}
}
