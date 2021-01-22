<template>
	<div class="fixed z-10 inset-0 overflow-y-auto">
		<div class="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0">
			<!--
				Background overlay, show/hide based on modal state.

				Entering: "ease-out duration-300"
					From: "opacity-0"
					To: "opacity-100"
				Leaving: "ease-in duration-200"
					From: "opacity-100"
					To: "opacity-0"
			-->
			<div aria-hidden="true" class="fixed inset-0 transition-opacity">
				<div class="absolute inset-0 bg-gray-500 opacity-75"></div>
			</div>

			<!-- This element is to trick the browser into centering the modal contents. -->
			<span aria-hidden="true" class="hidden sm:inline-block sm:align-middle sm:h-screen">&#8203;</span>
			<!--
				Modal panel, show/hide based on modal state.

				Entering: "ease-out duration-300"
					From: "opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
					To: "opacity-100 translate-y-0 sm:scale-100"
				Leaving: "ease-in duration-200"
					From: "opacity-100 translate-y-0 sm:scale-100"
					To: "opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
			-->
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
}
</script>

<style>

</style>
