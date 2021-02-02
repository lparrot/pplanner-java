import { Context } from '@nuxt/types'
import Vue from 'vue'

export default async (ctx: Context, inject) => {
	Vue.config.errorHandler = (err, vm, info) => {
		console.log(err, vm, info)
	}

	// ctx.app.router.addRoutes([
	// 	{ name: 'tasks-id', path: '/tasks/:id?' }
	// ])
}
