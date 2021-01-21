import { TwDropdown } from '~/.nuxt-storybook/components'

export default {
	title: 'Tailwind/tw-dropdown',
	component: TwDropdown,
}

export const Default = (args, { argTypes }) => ({
	props: Object.keys(argTypes),
	template: `
		<tw-dropdown v-bind="$props">Label</tw-dropdown>`,
})
