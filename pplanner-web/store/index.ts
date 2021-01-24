import { ActionTree, GetterTree, MutationTree, Store } from 'vuex'
import localforage from 'localforage'

const persistPlugin = async (store: Store<RootState>) => {
	localforage.config({
		driver: localforage.INDEXEDDB,
		name: 'pplanner',
		version: 1.0,
		storeName: 'pplanner'
	})
}

export const plugins = [ persistPlugin ]

/**
 * State
 */
export const state = () => ({
	selectedProject: null,
	selectedMenu: null,
	tasks: [],
})

export type RootState = ReturnType<typeof state>

/**
 * Getters
 */
export const getters: GetterTree<RootState, RootState> = {
	activeMenu: state => state.selectedMenu || localStorage.getItem('menu.' + state.selectedProject)
}


/**
 * Mutations
 */
export const mutations: MutationTree<RootState> = {
	SET_PROJECT (state, idProject) {
		state.selectedProject = idProject
	},
	SET_MENU (state, idMenu) {
		state.selectedMenu = idMenu
	},
}

/**
 * Actions
 */
export const actions: ActionTree<RootState, RootState> = {
	async logout () {
		await this.$auth.logout()
		await this.$router.push('/login')
	},

	selectProject ({ commit }, id) {
		commit('SET_PROJECT', id)
	},

	async selectMenu ({ state, commit }, id) {
		commit('SET_MENU', id)
		await localStorage.setItem(`menu.${ state.selectedProject }`, id)
	},
}
