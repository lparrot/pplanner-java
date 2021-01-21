import Vue from 'vue'

declare global {
	declare module 'vue-fragment' {
		class Fragment {
		}
	}
}

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
		auth?: boolean;
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

