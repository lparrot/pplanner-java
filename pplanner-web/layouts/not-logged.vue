<template>
	<div class="flex flex-col h-full">
		<div class="flex flex-col">
			<div class="flex bg-primary-700 text-white h-12 justify-between items-center">
				<ul class="flex items-center">
					<li class="mx-2"><img alt="logo" class="w-8 h-8" src="/logo.png"></li>
					<li class="mx-2">PPlanner</li>
				</ul>
				<ul class="flex items-center">
					<li class="mx-4">S'enregistrer</li>
					<li class="mx-4">Se connecter</li>
				</ul>
			</div>
		</div>
		<div class="flex flex-col h-full">
			<nuxt></nuxt>
		</div>
	</div>
</template>

<script lang="ts">
import {Component, Vue} from 'nuxt-property-decorator'

@Component
export default class LayoutNotLogged extends Vue {

}
</script>

<style scoped>

</style>
