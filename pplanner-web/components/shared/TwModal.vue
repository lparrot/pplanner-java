<template>
	<Transition appear name="fade">
		<div v-show="visibleSync" class="fixed z-10 inset-0 overflow-y-auto">
			<div class="flex items-center justify-center min-h-screen px-4 text-center block p-0">
				<div aria-hidden="true" class="fixed inset-0 transition-opacity">
					<div class="absolute inset-0 bg-gray-500 opacity-75" @click.self="hide"></div>
				</div>

				<span aria-hidden="true" class="sm:inline-block sm:align-middle sm:h-screen">&#8203;</span>

				<div :class="modalClasses" aria-labelledby="modal-headline" aria-modal="true" class="inline-block align-bottom bg-white rounded-lg text-left shadow-xl transform transition-all w-full sm:my-8 sm:align-middle" role="dialog">
					<button class="absolute w-4 h-4 ml-auto mt-2 right-2 top-0" @click="hide">
						<i class="fas fa-times text-sm text-gray-500"></i>
					</button>
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
	</Transition>
</template>

<script lang="ts">
import { Component, Prop, PropSync, Vue } from 'nuxt-property-decorator'

@Component({})
export default class TwModal extends Vue {
	@Prop() title !: string
	@Prop({ type: String, default: 'md' }) size !: 'sm' | 'md' | 'lg'
	@PropSync('visible') visibleSync!: any

	get modalClasses () {
		const classes = []
		switch (this.size) {
			case "sm":
				classes.push('sm:max-w-screen-md')
				break
			case "lg":
				classes.push('sm:max-w-screen-xl')
				break
			case "md":
			default:
				classes.push('sm:max-w-screen-lg')
				break
		}

		return classes
	}

	hide () {
		this.visibleSync = false
	}

	show () {
		this.visibleSync = true
		console.log(this.visibleSync);
	}

	toggle () {
		this.visibleSync = !this.visibleSync
	}
}
</script>

<style>

</style>
