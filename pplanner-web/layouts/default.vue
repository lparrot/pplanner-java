<template>
	<div class="h-full flex">
		<p-vertical-navbar :items="items" class="flex flex-col"></p-vertical-navbar>
		<nuxt ref="nuxt_navigation"></nuxt>
	</div>
</template>

<script lang="ts">
import {Component, Vue} from 'nuxt-property-decorator'
import PVerticalNavbar from "~/components/PVerticalNavbar.vue";

@Component({
	components: {
		PVerticalNavbar,
	},
})
export default class LayoutDefault extends Vue {
	public items: Models.PVerticalNavbarItem[] = []

	created() {
		this.items = [
			{name: 'Accueil', to: '/', icon: 'fas fa-home'},
			{name: 'Tâches', to: '/tasks', icon: 'fas fa-tasks'},
			{name: 'Notifications', to: '/notification', icon: 'fas fa-bell'},
			{name: 'Tableau de bord', to: '/dashboard', icon: 'fas fa-th-large'},
		]
	}
}
</script>

<style>

</style>
