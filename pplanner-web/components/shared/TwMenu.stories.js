import { TwMenu } from '~/.nuxt-storybook/components'

export default {
	title: 'Tailwind/TwMenu',
	component: TwMenu,
}

export const Default = (args, { argTypes }) => ({
	props: Object.keys(argTypes),
	template: `
		<tw-menu v-bind="$props"></tw-menu>`,
})
