import { Component, Vue } from 'nuxt-property-decorator'

@Component
export class TasksMixin extends Vue {
	public tasks: any[] = []

	async created () {
		this.$root.$on('pplanner:update-task-list', async () => {
			await this.fetchTasks()
		})
	}

	async fetchTasks () {
		this.tasks = await this.$api.tasks.findAllTasksByMenuItemId(this.$store.getters.activeMenu)
	}
}
