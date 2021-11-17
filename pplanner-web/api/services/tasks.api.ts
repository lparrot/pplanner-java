import { Repository } from '~/api/Repository'
import { ApiLog } from '~/decorators/ApiLog'

@ApiLog
export class TasksApi extends Repository {
	findAllTasksByMenuItemId (itemId: string) {
		if (itemId == null) {
			return []
		}
		return this.axios.$get(`/tasks/items/${ itemId }`)
	}

	createTask (task: Partial<Models.TaskEdit>) {
		if (task != null && task.item != null && task.item.id != null) {
			return this.axios.$post('/tasks/items/' + task.item.id, { description: task.description, name: task.name, statusId: task?.status?.id })
		}
	}

	updateTaskStatus (taskId, statusId) {
		if (taskId != null && statusId != null) {
			return this.axios.$put(`/tasks/${ taskId }/status/${ statusId }`)
		}
	}
}
