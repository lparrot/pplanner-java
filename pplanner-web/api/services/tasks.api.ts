import { Repository } from '~/api/Repository'
import { ApiLog } from '~/decorators/ApiLog'

@ApiLog
export class TasksApi extends Repository {
	findAllTasksByMenuItemId (itemId: string) {
		return this.axios.$get(`/tasks/items/${ itemId }`)
	}

	createTask (task: Models.TaskEdit) {
		return this.axios.$post('/tasks/items/' + task.item.id, task)
	}

	updateStatus (taskId, statusId) {
		return this.axios.$put(`/tasks/${ taskId }/status/${ statusId }`)
	}
}
