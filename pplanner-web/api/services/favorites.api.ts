import mock from '~/mocks/api.json'
import {Repository} from '~/api/Repository'

export class FavoritesApi extends Repository {
	findAllByProjectId(projectId) {
		return mock.projectItems.filter(item => item.id === 3 || item.id === 6 || item.id === 8)
	}
}
