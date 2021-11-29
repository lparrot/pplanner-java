const colors = require('tailwindcss/colors')
const {tailwindAnimations} = require('./tailwind-plugins/animations')

module.exports = {
	mode: 'jit',
	purge: {
		enabled: process.env.WEBPACK_DEV_SERVER === 'true' && process.argv.indexOf('build') !== -1,
		content: [
			'./projects/app/**/*.{html,ts,scss}',
			'./projects/**/*.{html,ts}',
		],
		options: {
			keyframes: true,
		},
	},
	darkMode: false, // or 'media' or 'class'
	theme: {
		extend: {
			colors: {
				'default': colorsWithDefault(colors.gray),
				'primary': colorsWithDefault(colors.blueGray),
				'secondary': colorsWithDefault(colors.violet),
				'success': colorsWithDefault(colors.green),
				'info': colorsWithDefault(colors.blue),
				'warn': colorsWithDefault(colors.orange),
				'danger': colorsWithDefault(colors.red),
			},
			fontFamily: {
				'sans': ['Proxima Nova', 'Roboto', 'Helvetica', 'Arial', 'sans-serif'],
			},
			...tailwindAnimations
		},
	},
	variants: {
		extend: {},
	},
	plugins: [
		require('@tailwindcss/forms')({
			strategy: 'class',
		}),
	],
}

function colorsWithDefault (colors) {
	return { ...colors, DEFAULT: colors['500'] }
}
