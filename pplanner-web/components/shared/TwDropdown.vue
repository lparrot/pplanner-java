<template>
	<div v-click-outside="() => visible = false" class="relative inline-block text-left">
		<div class="flex items-center gap-2 cursor-pointer" @click="toggle">
			<div>
				<slot name="activator"></slot>
			</div>
			<i v-if="showIcon" :class="{[iconOpen]: visible, [iconClose]: !visible}"></i>
		</div>
		<transition name="scale-in-ver-top">
			<div v-show="visible" class="origin-top-right absolute left-0 mt-2 w-56 rounded-md shadow-lg bg-white ring-1 ring-black ring-opacity-5 z-10">
				<slot></slot>
			</div>
		</transition>
	</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'nuxt-property-decorator'


@Component({})
export default class TwDropdown extends Vue {
	@Prop({ default: 'fas fa-caret-up' }) readonly iconOpen!: string
	@Prop({ default: 'fas fa-caret-down' }) readonly iconClose!: string
	@Prop({ type: Boolean, default: false }) readonly showIcon!: boolean

	visible: boolean = false

	toggle () {
		this.visible = !this.visible
	}
}
</script>

<style>

</style>
