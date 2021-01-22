<template>
	<div class="h-full flex">
		<app-vertical-navbar :items="items" class="flex flex-col">
			<template #bottom-icons>
				<i class="fas fa-sign-out-alt rounded-full p-2 hover:bg-primary-400 cursor-pointer" @click="logout"></i>
			</template>
		</app-vertical-navbar>
		<nuxt ref="nuxt_navigation"></nuxt>
	</div>
</template>

<script lang="ts">
import { Action, Component, Vue } from 'nuxt-property-decorator'
import AppVerticalNavbar from "~/components/app/AppVerticalNavbar.vue";

@Component({
	components: {
		AppVerticalNavbar,
	},
})
export default class LayoutDefault extends Vue {

	@Action('logout') logout

	public items: Models.PVerticalNavbarItem[] = []

	created () {
		this.items = [
			{ name: 'Tâches', to: '/tasks', icon: 'fas fa-tasks' },
			{ name: 'Notifications', to: '/notification', icon: 'fas fa-bell' },
			{ name: 'Tableau de bord', to: '/dashboard', icon: 'fas fa-th-large' },
		]
	}
}
</script>

<style>

</style>
