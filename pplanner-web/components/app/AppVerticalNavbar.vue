<template>
	<div class="flex text-primary-200 border-primary-200">
		<div class="flex flex-col w-16 h-full py-4 justify-start items-center bg-primary overflow-auto">
			<router-link to="/">
				<img alt="logo" class="w-8 p-1 mb-8 bg-white rounded" src="/logo.png">
			</router-link>

			<div id="navbar-content" class="h-full flex flex-col justify-between">
				<div class="flex flex-col gap-6 text-xl text-center">
					<template v-for="(item, itemIndex) in items">
						<div v-if="item.type === 'divider'" class="px-4 border-b"></div>
						<slot v-else-if="item.type === 'slot'" :name="item.name"></slot>
						<router-link v-else :key="itemIndex" :title="item.name" :to="item.to">
							<i :class="item.icon" class="with-bubble"></i>
						</router-link>
					</template>
				</div>

				<div class="flex flex-col gap-6 items-center text-xl">
					<slot name="bottom-icons"></slot>
					<hr class="w-full text-gray-700">
					<div class="flex justify-center items-center rounded-full w-10 h-10 bg-blue-900 text-white text-lg font-bold cursor-pointer" @click="$router.push('/profile')">
						LP
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'nuxt-property-decorator'

@Component({})
export default class AppVerticalNavbar extends Vue {
	@Prop() items!: Models.PVerticalNavbarItem[]
}
</script>

<style>
#navbar-content i.with-bubble {
	@apply cursor-pointer transform duration-300 ease-in-out hover:scale-150;
}
</style>
