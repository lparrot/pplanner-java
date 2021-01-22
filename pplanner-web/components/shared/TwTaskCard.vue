<template>
	<div class="bg-white shadow rounded px-3 pt-3 pb-5 border border-white">
		<div class="flex justify-between">
			<p class="text-gray-700 font-semibold font-sans tracking-wide text-sm">{{ task.title }}</p>

			<img alt="Avatar" class="w-6 h-6 rounded-full ml-3" src="https://pickaface.net/gallery/avatar/unr_sample_161118_2054_ynlrg.png">
		</div>
		<div class="flex mt-4 justify-between items-center">
			<span class="text-sm text-gray-600">{{ task.date }}</span>
			<tw-badge v-if="task.type" :color="badgeColor">{{ task.type }}</tw-badge>
		</div>
	</div>
</template>

<script lang="ts">
import TwBadge from './TwBadge.vue'
import { Component, Prop, Vue } from "nuxt-property-decorator";

@Component({
	components: {
		TwBadge,
	}
})
export default class TwTaskCard extends Vue {
	@Prop({ type: Object, default: () => ({}) }) task!: any

	get badgeColor () {
		const mappings = {
			Design: 'purple',
			'Feature Request': 'teal',
			Backend: 'blue',
			QA: 'green',
			default: 'teal',
		}
		return mappings[this.task.type] || mappings.default
	}
}
</script>
