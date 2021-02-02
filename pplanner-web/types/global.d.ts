import Vue from 'vue'
import { Auth } from '@nuxtjs/auth-next'

declare module '*.vue' {
	import Vue from 'vue'
	export default Vue
}

declare module 'vue/types/vue' {
	// this
	interface Vue {
		$api: import('~/api').Repositories
		$auth: Auth
		$bus: Vue
	}
}

declare module 'vue/types/options' {
	// option dans le composant
	interface ComponentOptions<V extends Vue> {
		auth?: Auth;
	}
}

declare module 'vuex' {
	interface Store {
		$api: import('~/api').Repositories
	}
}

declare module '@nuxt/types' {
	// ctx.app
	interface NuxtAppOptions {
		$api: import('~/api').Repositories
		$bus: Vue
	}

	// ctx
	interface Context {
		$api: import('~/api').Repositories
		$auth: Auth
		$bus: Vue
	}
}

