<template>
	<div class="flex flex-col border-r relative h-full">
		<transition name="scale-in-hor-left">
			<div v-show="visibleSync" class="transition-all duration-300 w-80 overflow-auto">
				<slot></slot>
			</div>
		</transition>
		<div class="absolute top-0 right-0">
			<div :class="altButtonClasses" class="flex justify-center items-center rounded-full bg-white w-6 h-6 text-gray-600 border -mr-3 mt-4 cursor-pointer" @click="handleClickAltButton">
				<i :class="altButtonIconClasses"></i>
			</div>
		</div>
	</div>
</template>

<script lang="ts">
import { Component, PropSync, Vue } from "nuxt-property-decorator";

@Component
export default class AppVerticalMenu extends Vue {
	@PropSync('visible') visibleSync!: any

	get altButtonClasses () {
		return this.visibleSync ? 'bg-white' : 'bg-secondary'
	}

	get altButtonIconClasses () {
		return this.visibleSync ? 'fas fa-caret-left mr-0.5' : 'text-white fas fa-caret-right -mr-0.5'
	}

	handleClickAltButton () {
		this.visibleSync = !this.visibleSync
	}
}
</script>
