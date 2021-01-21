<template>
	<div class="flex items-center gap-2 cursor-pointer" @click="toggle">
		<slot></slot>
		<i :class="{[iconOpen]: opened, [iconClose]: !opened}"></i>
	</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'nuxt-property-decorator'


@Component({})
export default class TwDropdown extends Vue {
	@Prop({ default: 'fas fa-caret-up' }) iconOpen: string
	@Prop({ default: 'fas fa-caret-down' }) iconClose: string

	opened: boolean = false

	toggle () {
		this.opened = !this.opened
	}
}
</script>

<style>

</style>
