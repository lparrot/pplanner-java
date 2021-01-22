import { TwModal } from '~/.nuxt-storybook/components'

export default {
	title: 'Tailwind/tw-modal',
	component: TwModal,
}

export const Default = (args, { argTypes }) => ({
	props: Object.keys(argTypes),
	template: `
		<tw-modal v-bind="$props">
		Voici le contenu de ma modale
		<template #actions>
			<button class="p-btn p-btn--primary">Fermer</button>
			<button class="p-btn p-btn--secondary">Valider</button>
		</template>
		</tw-modal>`,
})

export const WithTitle = Default.bind({})
WithTitle.args = { title: 'Titre de la modale' }
