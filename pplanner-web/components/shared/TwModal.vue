<template>
	<div v-show="visible" class="fixed z-10 inset-0 overflow-y-auto">
		<div class="flex items-center justify-center min-h-screen pt-4 px-4  text-center block p-0">
			<div aria-hidden="true" class="fixed inset-0 transition-opacity">
				<div class="absolute inset-0 bg-gray-500 opacity-75"></div>
			</div>

			<span aria-hidden="true" class="hidden sm:inline-block sm:align-middle sm:h-screen">&#8203;</span>

			<div aria-labelledby="modal-headline" aria-modal="true" class="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full" role="dialog">
				<div class="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
					<div class="">
						<div v-if="title != null" class="text-medium text-lg">{{ title }}</div>
						<div>
							<slot></slot>
						</div>
					</div>
				</div>
				<div class="bg-gray-50 px-4 py-3 sm:px-6 sm:flex justify-end gap-2">
					<slot name="actions"></slot>
				</div>
			</div>
		</div>
	</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'nuxt-property-decorator'

@Component({})
export default class TwModal extends Vue {
	@Prop() title !: string
	@Prop() visible!: boolean

	hide () {
		this.visible = false
	}

	show () {
		this.visible = true
	}

	toggle () {
		this.visible = !this.visible
	}
}
</script>

<style>

</style>
