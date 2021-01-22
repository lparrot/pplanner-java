import { TwTaskCard } from '~/.nuxt-storybook/components'

export default {
	title: 'Tailwind/TwTaskCard',
	component: TwTaskCard,
}

export const Default = (args, { argTypes }) => ({
	props: Object.keys(argTypes),
	template: `
		<tw-task-card v-bind="$props"></tw-task-card>`,
})
