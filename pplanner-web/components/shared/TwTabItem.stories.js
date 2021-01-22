import { TwTabItem } from '~/.nuxt-storybook/components'

export default {
	title: 'Tailwind/TwTabItem',
	component: TwTabItem,
}

export const Default = (args, { argTypes }) => ({
	props: Object.keys(argTypes),
	template: `
		<tw-tab-item v-bind="$props"></tw-tab-item>`,
})
