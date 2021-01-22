import { TwBadge } from '~/.nuxt-storybook/components'

export default {
	title: 'Tailwind/TwBadge',
	component: TwBadge,
}

export const Default = (args, { argTypes }) => ({
	props: Object.keys(argTypes),
	template: `
		<tw-badge v-bind="$props"></tw-badge>`,
})
