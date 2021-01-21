<template>
	<div class="flex">
		<div class="flex flex-col w-16 h-full py-4 justify-start items-center bg-primary text-white overflow-auto">
			<router-link to="/">
				<img alt="logo" class="w-8 p-1 mb-8 bg-white rounded" src="/logo.png">
			</router-link>

			<div id="navbar-content" class="h-full flex flex-col items-center justify-between">
				<div class="flex flex-col gap-6 text-xl">
					<router-link v-for="(item, itemIndex) in items" :key="itemIndex" :title="item.name" :to="item.to">
						<i :class="item.icon"></i>
					</router-link>
				</div>
				<div class="flex flex-col gap-6 items-center text-xl">
					<i class="fas fa-sign-in-alt"></i>
					<i class="fas fa-sign-out-alt"></i>
					<hr class="w-full text-gray-700">
					<div
						class="flex justify-center items-center rounded-full w-10 h-10 bg-blue-900 text-white text-lg font-bold cursor-pointer"
						@click="$router.push('profile')">
						LP
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script lang="ts">
import {Component, Prop, Vue} from 'nuxt-property-decorator'
import {Fragment} from 'vue-fragment'

@Component({
	components: {
		Fragment,
	},
})
export default class PVerticalNavbar extends Vue {
	@Prop() items!: Models.PVerticalNavbarItem[]
}
</script>

<style>
#navbar-content i {
	@apply cursor-pointer transform duration-300 ease-in-out hover:scale-150;
}
</style>
