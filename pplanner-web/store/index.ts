import {ActionTree, GetterTree, MutationTree} from 'vuex'
import Vue from 'vue'

/**
 * State
 */
export const state = () => ({
	selectedProject: null,
	selectedProjectItem: {},
	tasks: [],
})

export type RootState = ReturnType<typeof state>

/**
 * Getters
 */
export const getters: GetterTree<RootState, RootState> = {
	activeListItem: state => state.selectedProjectItem[state.selectedProject],
}


/**
 * Mutations
 */
export const mutations: MutationTree<RootState> = {
	SET_PROJECT(state, idProject) {
		state.selectedProject = idProject
	},
	SET_PROJECT_ITEM(state, idWorkspace) {
		Vue.set(state.selectedProjectItem, state.selectedProject, idWorkspace)
	},
}

/**
 * Actions
 */
export const actions: ActionTree<RootState, RootState> = {
	selectProject({commit}, id) {
		commit('SET_PROJECT', id)
	},

	selectProjectItem({state, commit}, id) {
		commit('SET_PROJECT_ITEM', id)
	},
}
