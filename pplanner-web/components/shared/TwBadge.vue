<template>
	<div :class="`bg-${color}-100 text-${color}-700`" class="px-3 h-6 rounded-full text-xs font-semibold flex items-center">
		<span :class="`bg-${color}-400`" class="w-2 h-2 rounded-full mr-1"></span>
		<span>
      <slot></slot>
    </span>
	</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "nuxt-property-decorator";

@Component({})
export default class TwBadge extends Vue {
	@Prop({ type: String, default: 'primary' }) color !: string
}
</script>
