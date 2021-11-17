<template>
	<div class="bg-white shadow rounded px-3 pt-3 pb-5 border border-white">
		<div class="flex justify-between">
			<p class="text-gray-700 font-semibold font-sans tracking-wide text-sm">{{ task.name }}</p>

			<img :src="'https://i.pravatar.cc/300?img=' + Math.random() * (70 - 1) + 1" alt="Avatar" class="w-6 h-6 rounded-full ml-3">
		</div>
		<div class="flex mt-4 justify-between items-center">
			<span class="text-sm text-gray-600">{{ task.createdAt }}</span>
			<tw-badge color="primary">{{ task.creatorName }}</tw-badge>
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
}
</script>
