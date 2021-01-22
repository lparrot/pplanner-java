<template>
	<div :class="{'bg-primary-100 border-t border-l border-r': active, 'text-primary-400': !active}" class="px-4 py-3 rounded-t-xl cursor-pointer hover:bg-secondary-100" @click="selectTab">
		<slot></slot>
	</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'nuxt-property-decorator'

@Component({})
export default class TwTabItem extends Vue {

	@Prop({ required: true }) name!: string
	@Prop() active!: boolean

	selectTab () {
		this.$bus.$emit('on-select-view-tab', { name: this.name })
	}

}
</script>

<style>

</style>
