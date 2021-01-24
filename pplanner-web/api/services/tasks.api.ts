import { Repository } from '~/api/Repository'

export class TasksApi extends Repository {
	findAllTasksByMenuItemId (itemId: string) {
		return this.axios.$get(`/tasks/items/${ itemId }`)
	}

	createTask (task: Models.TaskEdit) {
		return this.axios.$post('/tasks/items/' + task.item.id, task)
	}
}
