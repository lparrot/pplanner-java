import { Repository } from '~/api/Repository'
import { ApiLog } from '~/decorators/ApiLog'

@ApiLog
export class FavoritesApi extends Repository {
	findAllByProjectId () {
		if (this.store.getters.activeProject == null) {
			return []
		}
		return this.axios.$get(`/favorites/users/${ this.store.state.auth.user.id }/projects/${ this.store.getters.activeProject }`)
	}

	async create (itemId) {
		return this.axios.$post('/favorites', { userId: this.store.state.auth.user.id, menuId: itemId })
	}

	async delete (favoriteId) {
		return this.axios.$delete(`/favorites/${ favoriteId }`)
	}
}
