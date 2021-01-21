import {Repository} from '~/api/Repository'


export class MenuItemsApi extends Repository {
	findAllByProjectId(projectId) {
		return this.axios.$get(`/projects/${projectId}/workspaces`)
	}
}

