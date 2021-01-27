<template>
	<div @click="$emit('click', $event)">
		<hr v-if="type === 'separator'">
		<div v-else-if="type === 'label'" class=" py-2 text-primary-400 uppercase font-medium">
			<slot>
				{{ label }}
			</slot>
		</div>
		<div v-else-if="type === 'text'" class="flex items-center justify-between rounded p-2 my-1 cursor-pointer hover:bg-primary-100 whitespace-nowrap">
			<div>
				<slot name="icon">
					<i :class="icon" class="text-primary-400 mr-2"></i>
				</slot>
				<slot>
					{{ label }}
				</slot>
			</div>
			<div>
				<slot name="right"></slot>
			</div>
		</div>
	</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'nuxt-property-decorator'

@Component({})
export default class TwMenuItem extends Vue {
	@Prop() readonly icon !: string
	@Prop() readonly label !: string
	@Prop({ default: 'text' }) readonly type !: 'text' | 'separator' | 'label'
}
</script>

<style>

</style>
