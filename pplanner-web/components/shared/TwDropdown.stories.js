import { TwDropdown } from '~/.nuxt-storybook/components'

export default {
	title: 'Tailwind/TwDropdown',
	component: TwDropdown,
}

export const Default = (args, { argTypes }) => ({
	props: Object.keys(argTypes),
	template: `
		<tw-dropdown v-bind="$props">
		<template #activator>
			Label
		</template>
		<div class="p-2">

		</div>
		</tw-dropdown>`,
})
