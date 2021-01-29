import { Component, Vue } from 'nuxt-property-decorator'
import { VModel } from 'vue-property-decorator'

@Component
export class TasksMixin extends Vue {
	@VModel() item: Models.ProjectMenuItem

	public tasks: any = null
	public statusList: any[] = []

	async created () {
		this.$root.$on('pplanner:update-task-list', async () => {
			await this.fetchData()
		})
	}

	async fetchData () {
		this.tasks = await this.$api.tasks.findAllTasksByMenuItemId(this.$store.getters.activeMenu)
		this.statusList = await this.$axios.$get(`/task_status/items/${ this.item.id }`)
	}
}
