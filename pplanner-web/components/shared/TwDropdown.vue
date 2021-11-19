<template>
	<div class="relative inline-block text-left">
		<div :class="{'z-10': visible}" class="flex relative items-center gap-2 cursor-pointer" @click="toggle">
			<slot name="activator"></slot>
			<i v-if="showIcon" :class="{[iconOpen]: visible, [iconClose]: !visible}"></i>
		</div>
		<button v-if="visible" class="fixed inset-0 top-0 left-0 h-full w-full cursor-default" tabindex="-1" @click="visible = false"></button>
		<transition name="scale-in-ver-top">
			<div v-show="visible" :class="[containerClass, { 'fixed': fixed, 'absolute': !fixed }]" class="z-10 mt-2 rounded-md shadow-lg bg-white ring-1 ring-black ring-opacity-5 w-auto">
				<slot :hide="hide" :show="show" :toggle="toggle"></slot>
			</div>
		</transition>
	</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'nuxt-property-decorator'

@Component({})
export default class TwDropdown extends Vue {
	@Prop({ type: Boolean, default: false }) readonly fixed !: boolean
	@Prop({ type: String, default: 'fas fa-caret-up' }) readonly iconOpen!: string
	@Prop({ type: String, default: 'fas fa-caret-down' }) readonly iconClose!: string
	@Prop({ type: Boolean, default: false }) readonly showIcon!: boolean
	@Prop({ type: String, default: '' }) readonly containerClass!: string

	public visible: boolean = false

	created () {
		const handleEscape = (e) => {
			if (e.key === 'Esc' || e.key === 'Escape') {
				this.visible = false
			}
		}

		document.addEventListener('keydown', handleEscape)
		this.$once('hook:beforeDestroy', () => {
			document.removeEventListener('keydown', handleEscape)
		})

	}

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
