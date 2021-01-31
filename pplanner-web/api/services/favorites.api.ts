import { Repository } from '~/api/Repository'
import { ApiLog } from '~/decorators/ApiLog'

@ApiLog
export class FavoritesApi extends Repository {
	findAllByProjectId () {
		if (this.store.getters.activeProject == null) {
			return []
		}
		return this.axios.$get(`/favorites/projects/${ this.store.getters.activeProject }`)
	}

	async create (itemId) {
		return this.axios.$post('/favorites', { menuId: itemId })
	}

	async delete (favoriteId) {
		return this.axios.$delete(`/favorites/${ favoriteId }`)
	}
}
