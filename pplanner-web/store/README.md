# STORE

**This directory is not required, you can delete it if you don't want to use it.**

This directory contains your Vuex Store files. Vuex Store option is implemented in the Nuxt.js framework.

Creating a file in this directory automatically activates the option in the framework.

More information about the usage of this directory in [the documentation](https://nuxtjs.org/guide/vuex-store).

Template :

```ts
import { ActionTree, GetterTree, MutationTree } from 'vuex'

/**
 * State
 */
export const state = () => ({
	xxx: null
})

export type RootState = ReturnType<typeof state>

/**
 * Getters
 */
export const getters: GetterTree<RootState, RootState> = {}


/**
 * Mutations
 */
export const mutations: MutationTree<RootState> = {
	SET_XXX (state, payload) {
		state.xxx = payload
	},
}

/**
 * Actions
 */
export const actions: ActionTree<RootState, RootState> = {
	async xxx ({ commit }, payload) {

	},
}
```
