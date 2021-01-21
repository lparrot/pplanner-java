<template>
	<div class="flex flex-col h-full">
		<div class="flex flex-col">
			<div class="flex bg-primary-700 text-white h-12 justify-between items-center">
				<ul class="flex items-center">
					<li class="mx-2"><img alt="logo" class="w-8 h-8" src="/logo.png"></li>
					<router-link class="mx-2 font-medium text-lg cursor-pointer px-2 py-1 rounded-full duration-300 hover:bg-primary" tag="li" to="/">PPlanner</router-link>
				</ul>
				<ul class="flex items-center font-medium">
					<template v-if="$auth.loggedIn">
						<router-link class="mx-2 px-2 py-2 rounded-full cursor-pointer duration-300 hover:bg-primary" tag="li" to="/tasks">
							<i class="fas fa-registered mr-1"></i>
							<span>Tableau de bord</span>
						</router-link>
						<li class="mx-2 px-2 py-2 rounded-full cursor-pointer duration-300 hover:bg-primary" @click="logout">
							<i class="fas fa-sign-in-alt mr-1"></i>
							<span>Se déconnecter</span>
						</li>
					</template>
					<template v-else>
						<router-link class="mx-2 px-2 py-2 rounded-full cursor-pointer duration-300 hover:bg-primary" tag="li" to="/register">
							<i class="fas fa-registered mr-1"></i>
							<span>S'enregistrer</span>
						</router-link>
						<router-link class="mx-2 px-2 py-2 rounded-full cursor-pointer duration-300 hover:bg-primary" tag="li" to="/login">
							<i class="fas fa-sign-in-alt mr-1"></i>
							<span>Se connecter</span>
						</router-link>
					</template>
				</ul>
			</div>
		</div>
		<div class="flex flex-col h-full px-4">
			<transition appear name="flip-in-hor-top">
				<nuxt></nuxt>
			</transition>
		</div>
	</div>
</template>

<script lang="ts">
import { Action, Component, Vue } from 'nuxt-property-decorator'

@Component
export default class LayoutNotLogged extends Vue {
	@Action('logout') logout;
}
</script>

<style scoped>

</style>
