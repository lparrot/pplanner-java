<template>
	<div class="h-full flex">
		<p-vertical-navbar :items="items" class="flex flex-col">
			<template #bottom-icons>
				<i class="fas fa-sign-out-alt rounded-full p-2 hover:bg-primary-400 cursor-pointer" @click="logout"></i>
			</template>
		</p-vertical-navbar>
		<nuxt ref="nuxt_navigation"></nuxt>
	</div>
</template>

<script lang="ts">
import { Action, Component, Vue } from 'nuxt-property-decorator'
import PVerticalNavbar from "~/components/PVerticalNavbar.vue";

@Component({
	components: {
		PVerticalNavbar,
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
