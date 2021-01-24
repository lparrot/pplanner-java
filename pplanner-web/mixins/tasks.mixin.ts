import { Component, Vue } from 'nuxt-property-decorator'

@Component
export class TasksMixin extends Vue {
	public tasks: any[] = []

	async fetchTasks () {
		this.tasks = await this.$api.tasks.findAllTasksByMenuItemId(this.$store.state.selectedMenu)
	}
}
