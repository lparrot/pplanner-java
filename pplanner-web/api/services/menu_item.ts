import { Repository } from '~/api/Repository'
import { ApiLog } from '~/decorators/ApiLog'

@ApiLog
export class MenuItemsApi extends Repository {
	findById (itemId: string) {
		if (itemId == null) {
			return null
		}
		return this.axios.$get(`/items/${ itemId }`)
	}

	findFirstWorkspaceByProjectId (projectId: string) {
		if (projectId == null) {
			return []
		}
		return this.axios.$get(`/items/projects/${ projectId }/first`)
	}
}

