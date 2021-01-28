import { Component, Vue } from 'nuxt-property-decorator'
import { VModel } from 'vue-property-decorator'

@Component
export class TasksMixin extends Vue {
	@VModel() item: Models.ProjectMenuItem

	public tasks: any = null

	async created () {
		this.$root.$on('pplanner:update-task-list', async () => {
			await this.fetchTasks()
		})
	}

	async fetchTasks () {
		this.tasks = await this.$api.tasks.findAllTasksByMenuItemId(this.$store.getters.activeMenu)
	}
}
