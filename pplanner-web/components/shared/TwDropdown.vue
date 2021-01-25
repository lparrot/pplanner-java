<template>
	<div v-click-outside="() => visible = false" class="relative inline-block text-left">
		<div class="flex items-center gap-2 cursor-pointer" @click="toggle">
			<slot name="activator"></slot>
			<i v-if="showIcon" :class="{[iconOpen]: visible, [iconClose]: !visible}"></i>
		</div>
		<div :class="{'fixed sm:w-72': fixed}" class="z-50">
			<transition name="scale-in-ver-top">
				<div v-show="visible" :class="[width]" class="fixed mt-2 rounded-md shadow-lg bg-white ring-1 ring-black ring-opacity-5 z-10 sm:absolute left-0 sm:origin-top-right">
					<slot :hide="hide" :show="show" :toggle="toggle"></slot>
				</div>
			</transition>
		</div>
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
	@Prop({ type: String, default: 'w-full' }) readonly width!: string

	public visible: boolean = false

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
