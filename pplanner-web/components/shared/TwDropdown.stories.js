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
			<tw-menu>
				<tw-menu-item type="label">Common</tw-menu-item>
				<tw-menu-item icon="fas fa-home">
					<span>Dashboard</span>
					<template #right>
						<tw-badge color="primary">5</tw-badge>
					</template>
				</tw-menu-item>

				<tw-menu-item type="separator"></tw-menu-item>
				<tw-menu-item type="label">Admin</tw-menu-item>

				<tw-menu-item icon="fas fa-users">
					<span>Team</span>
				</tw-menu-item>
				<tw-menu-item icon="fas fa-home">
					<span>Projects</span>
					<template #right>
						<tw-badge color="info">20+</tw-badge>
					</template>
				</tw-menu-item>
			</tw-menu>
		</div>
		</tw-dropdown>`,
})
