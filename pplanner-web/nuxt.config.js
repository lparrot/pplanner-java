export default {
	// Disable server-side rendering (https://go.nuxtjs.dev/ssr-mode)
	ssr: false,

	// Target (https://go.nuxtjs.dev/config-target)
	target: 'static',

	static: {
		// Aucun préfixe ne sera rajouté au niveau du baseUrl pour les images par exemple
		prefix: false,
	},

	router: {
		middleware: ['auth'],
	},

	// Global page headers (https://go.nuxtjs.dev/config-head)
	head: {
		title: 'PPlanner',
		meta: [
			{ charset: 'utf-8' },
			{ name: 'viewport', content: 'width=device-width, initial-scale=1' },
			{ hid: 'description', name: 'description', content: '' },
		],
		link: [
			{ rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' },
		],
	},

	// Global CSS (https://go.nuxtjs.dev/config-css)
	css: [
		'@fortawesome/fontawesome-free/css/all.css',
		'@/assets/styles/app.scss',
	],

	// Plugins to run before rendering page (https://go.nuxtjs.dev/config-plugins)
	plugins: [
		'~/plugins/vee-validate',
		'~/plugins/api',
	],

	// Auto import components (https://go.nuxtjs.dev/config-components)
	components: [
		'~/components/',
		{ path: '~/components/views', global: true },
		{ path: '~/components/ihm', global: true },
	],

	// Modules for dev and build (recommended) (https://go.nuxtjs.dev/config-modules)
	buildModules: [
		// https://go.nuxtjs.dev/typescript
		'@nuxt/typescript-build',
		// https://go.nuxtjs.dev/tailwindcss
		'@nuxtjs/tailwindcss',
	],

	// Modules (https://go.nuxtjs.dev/config-modules)
	modules: [
		// https://go.nuxtjs.dev/axios
		'@nuxtjs/axios',
		// https://go.nuxtjs.dev/pwa
		'@nuxtjs/pwa',
		'@nuxtjs/auth-next',
		'@/modules/app',
	],

	// Axios module configuration (https://go.nuxtjs.dev/config-axios)
	axios: {
		prefix: '/api',
		proxy: true,
	},

	proxy: {
		'/api': {
			target: 'http://[::1]:8080',
			changeOrigin: false,
		},
	},

	auth: {
		plugins: ['~/plugins/auth'],
		cookie: false,
		watchLoggedIn: false,
		strategies: {
			local: {
				token: {
					property: 'token',
				},
				user: {
					property: false,
				},
				endpoints: {
					login: { url: '/auth/login', method: 'post' },
					user: { url: '/auth/user', method: 'get' },
				},
			},
		},
		redirect: {
			callback: false,
			home: '/',
			login: '/login',
			logout: '/login',
		},
	},

	// Build Configuration (https://go.nuxtjs.dev/config-build)
	build: {
		extractCSS: true,
		postcss: {
			preset: {
				features: {
					// Fixes: https://github.com/tailwindcss/tailwindcss/issues/1190#issuecomment-546621554
					'focus-within-pseudo-class': false,
				},
			},
		},
	},
}
