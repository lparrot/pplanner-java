import { Repository } from '~/api/Repository'


export class MenuItemsApi extends Repository {
	findAllByProjectId (id: string) {
		return this.axios.$get(`/projects/${ id }/workspaces`)
	}

	findById (id: string) {
		return this.axios.$get(`/items/${ id }`)
	}
}

