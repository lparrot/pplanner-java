import { Repository } from '~/api/Repository'

export class FavoritesApi extends Repository {
	findAllByProjectId (projectId) {
		return this.axios.$get(`/favorites/users/${ this.store.state.auth.user.id }/projects/${ this.store.state.selectedProject }`)
	}

	async create (itemId) {
		return this.axios.$post('/favorites', { userId: this.store.state.auth.user.id, menuId: itemId })
	}

	async delete (favoriteId) {
		return this.axios.$delete(`/favorites/${ favoriteId }`)
	}
}
