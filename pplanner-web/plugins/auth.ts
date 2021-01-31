import { Context } from '@nuxt/types'

export default async (ctx: Context, inject) => {
	// TODO : A supprimer à terme
	await ctx.store.dispatch('initialize')

	if (ctx.store.getters.activeProject == null && ctx.$auth.loggedIn) {
		await ctx.store.dispatch('selectFirstProject')
	}
}
