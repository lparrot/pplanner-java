import { Store } from 'vuex'
import { NuxtAxiosInstance } from '@nuxtjs/axios'
import { FavoritesApi } from '~/api/services/favorites.api'
import { ProjectsApi } from '~/api/services/projects.api'
import { TasksApi } from '~/api/services/tasks.api'
import { MenuItemsApi } from '~/api/services/menu_item'
import { AuthApi } from '~/api/services/auth.api'
import { TaskStatusApi } from '~/api/services/task_status.api'

export interface Repositories {
	auth: AuthApi
	favorites: FavoritesApi
	items: MenuItemsApi
	projects: ProjectsApi
	tasks: TasksApi
	task_status: TaskStatusApi
}

export class Api {
	private readonly _store: Store<any>
	private readonly _axios: NuxtAxiosInstance

	constructor (store, axios) {
		this._store = store
		this._axios = axios

		this._createRepository('auth', AuthApi)
		this._createRepository('favorites', FavoritesApi)
		this._createRepository('items', MenuItemsApi)
		this._createRepository('projects', ProjectsApi)
		this._createRepository('tasks', TasksApi)
		this._createRepository('task_status', TaskStatusApi)
	}

	_createRepository (name, type) {
		this[name] = Reflect.construct(type, [ this._store, this._axios ])
	}
}
