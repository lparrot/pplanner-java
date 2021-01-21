import {NuxtAxiosInstance} from '@nuxtjs/axios'
import {Store} from 'vuex'

export class Repository {
	public store: Store<any>
	public axios: NuxtAxiosInstance

	constructor(store, axios) {
		this.store = store
		this.axios = axios
	}
}
