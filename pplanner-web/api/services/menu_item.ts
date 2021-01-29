import { Repository } from '~/api/Repository'
import { ApiLog } from '~/decorators/ApiLog'

@ApiLog
export class MenuItemsApi extends Repository {
	findAllByProjectId (id: string) {
		return this.axios.$get(`/projects/${ id }/workspaces`)
	}

	findById (id: string) {
		return this.axios.$get(`/items/${ id }`)
	}

	findFirstWorkspaceByProjectId (projectId: string) {
		return this.axios.$get(`/items/projects/${ projectId }/first`)
	}
}

