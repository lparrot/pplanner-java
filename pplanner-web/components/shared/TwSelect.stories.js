import { TwSelect } from '~/.nuxt-storybook/components'

export default {
	title: 'Tailwind/TwSelect',
	component: TwSelect,
}

export const Default = (args, { argTypes }) => ({
	props: Object.keys(argTypes),
	template: `
		<tw-select v-bind="$props"></tw-select>`,
})

export const WithLabel = Default.bind({})
WithLabel.args = { label: 'Label du select' }
