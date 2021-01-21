import {Api} from '~/api'
import Vue from 'vue'
import {Context} from '@nuxt/types'

export let api = null

export default async (ctx: Context, inject) => {
	api = new Api(ctx.store, ctx.$axios)

	inject('api', api)
	inject('bus', new Vue())
}
