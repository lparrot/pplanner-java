import { TwInputText } from '~/.nuxt-storybook/components'

export default {
	title: 'Tailwind/TwInputText',
	component: TwInputText,
}

const Default = (args, { argTypes }) => ({
	props: Object.keys(argTypes),
	template: `
		<tw-input-text v-bind="$props">Label</tw-input-text>`,
})

export const WithError = Default.bind({})
WithError.args = { error: true, errorMessage: 'Une erreur est survenue' }

export const WithLabel = Default.bind({})
WithLabel.args = { label: `Label` }

export const WithRequiredLabel = Default.bind({})
WithRequiredLabel.args = { label: `Label requis`, required: true }

export const Password = Default.bind({})
Password.template = `<tw-input-text v-bind="$props" type="password">Label</tw-input-text>\`,`
