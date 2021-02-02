import { ActionTree, GetterTree, MutationTree } from 'vuex'

/**
 * State
 */
export const state = () => ({
	selectedProject: null,
	selectedMenu: null,
})

export type RootState = ReturnType<typeof state>

/**
 * Getters
 */
export const getters: GetterTree<RootState, RootState> = {
	activeProject: state => state.selectedProject || localStorage.getItem('pplanner.project'),
	activeMenu: state => state.selectedMenu || localStorage.getItem('pplanner.menu-' + state.selectedProject)
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
	async goToTaskIdListPage ({ state }) {
		this.$router.push({ name: 'tasks-id', params: { id: state.selectedMenu }, query: { view: 'list' } })
	},

	async initialize ({ commit }) {
		const projectId = localStorage.getItem('pplanner.project')
		if (projectId != null) {
			try {
				await this.$api.projects.findById(projectId)
				commit('SET_PROJECT', projectId)
				const menuId = localStorage.getItem(`pplanner.menu-${ projectId }`)
				if (menuId != null) {
					await this.$api.items.findById(menuId)
					commit('SET_MENU', menuId)
				}
			} catch (err) {
				if (err.response.status === 404) {
					localStorage.removeItem('pplanner.project')
					localStorage.removeItem(`pplanner.menu-${ projectId }`)
				}
			}
		}
	},

	async logout () {
		await this.$auth.logout()
		await this.$router.push('/login')
	},

	async refreshData () {
		console.log(this)
	},

	async selectMenu ({ getters, commit }, id) {
		commit('SET_MENU', id)
		if (id == null) {
			localStorage.removeItem(`pplanner.menu-${ getters.activeProject }`)
		} else {
			localStorage.setItem(`pplanner.menu-${ getters.activeProject }`, id)
		}
	},

	async selectProject ({ commit }, id) {
		commit('SET_PROJECT', id)
		commit('SET_MENU', null)
		if (id == null) {
			localStorage.removeItem(`pplanner.project`)
		} else {
			localStorage.setItem(`pplanner.project`, id)
		}
	},

	async selectFirstProject ({ dispatch }) {
		const res = await this.$api.projects.findAllByCreatorAndMember()
		if (res.length > 0) {
			await dispatch('selectProject', res[0].id)
		}
	}
}
