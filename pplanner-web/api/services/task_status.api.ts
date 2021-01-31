import { Repository } from '~/api/Repository'
import { ApiLog } from '~/decorators/ApiLog'

@ApiLog
export class TaskStatusApi extends Repository {
	async findStatusByItemId (itemId) {
		if (itemId == null) {
			return []
		}
		return this.axios.$get(`/task_status/items/${ itemId }`)
	}
}
