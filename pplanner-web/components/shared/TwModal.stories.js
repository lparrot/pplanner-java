import { TwModal } from '~/.nuxt-storybook/components'

export default {
	title: 'Tailwind/TwModal',
	component: TwModal,
}

export const Default = (args, { argTypes }) => ({
	props: Object.keys(argTypes),
	template: `
		<div>
		<button class="p-btn p-btn--primary" @click="$refs.modal.show()">Ouvrir modal</button>

		<tw-modal ref="modal" v-bind="$props">
			Voici le contenu de ma modale
			<template #actions>
				<button @click="$refs.modal.hide()" class="p-btn p-btn--primary">Fermer</button>
				<button class="p-btn p-btn--secondary">Valider</button>
			</template>
		</tw-modal>
		</div>`,
})

export const WithTitle = Default.bind({})
WithTitle.args = { title: 'Titre de la modale' }

export const AlreadyOpened = (args, { argTypes }) => ({
	props: Object.keys(argTypes),
	template: `
		<tw-modal v-bind="$props" :visible="true">
		Voici le contenu de ma modale
		<template #actions>
			<button class="p-btn p-btn--primary">Fermer</button>
			<button class="p-btn p-btn--secondary">Valider</button>
		</template>
		</tw-modal>`,
})
