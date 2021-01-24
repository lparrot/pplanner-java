<template>
	<fragment>
		<app-vertical-menu :visible.sync="visible">
			<div class="p-6">
				<!-- Favorites -->

				<div class="mb-5">
					<div class="flex justify-between items-center cursor-pointer hover:text-secondary" @click="show.favorites = !show.favorites" @mouseenter="show.favoriteActions = true" @mouseleave="show.favoriteActions = false">
						<div class="text-xl font-medium">Favoris</div>
						<transition name="focus-in-contract">
							<div v-show="show.favoriteActions" class="font-medium uppercase">{{ show.favorites ? 'Cacher' : 'Voir' }}</div>
						</transition>
					</div>
					<transition name="scale-up-ver-top">
						<div v-show="show.favorites" class="text-xs">
							<template v-if="favorites.length < 1">
								Vous n'avez aucun favoris pour le moment.
							</template>
							<template v-else>
								<div v-for="favorite in favorites" :key="favorite.id" :class="{'text-secondary': selectedMenu === favorite.id}" class="flex justify-between items-center cursor-pointer text-primary hover:text-secondary">
									<div class="ml-1 mt-2 hover:underline" @click="handleClickFavorite(favorite)">{{ favorite.name }}</div>
									<i class="fas fa-ellipsis-h"></i>
								</div>
							</template>
						</div>
					</transition>
				</div>

				<!-- Workspaces -->
				<div class="flex justify-between items-center pb-5">
					<div class="text-xl font-medium">Espaces de travail</div>
					<div class="flex gap-4 text-lg">
						<i class="fas fa-cog text-gray-400 cursor-pointer hover:text-secondary"></i>
						<i class="fas fa-search text-gray-400 cursor-pointer hover:text-secondary"></i>
					</div>
				</div>

				<app-project-menu-item-container @input="handleSelectMenuItem"></app-project-menu-item-container>

				<div class="text-gray-400 hover:text-secondary cursor-pointer mt-4">
					<i class="fas fa-plus mr-2"></i>
					<span>Nouvel espace de travail</span>
				</div>
			</div>
		</app-vertical-menu>

		<div class="p-container h-full">
			<nuxt-child :key="$route.fullPath"></nuxt-child>
		</div>
	</fragment>
</template>

<script lang="ts">
import { Action, Component, State, Vue } from "nuxt-property-decorator";
import AppVerticalMenu from "../components/app/AppVerticalMenu.vue";
import { Fragment } from 'vue-fragment'
import AppProjectMenuItem from "~/components/app/AppProjectMenuItem.vue";
import TwModal from "~/components/shared/TwModal.vue";
import AppProjectMenuItemContainer from "~/components/app/AppProjectMenuItemContainer.vue";

@Component({
	components: {
		AppProjectMenuItemContainer,
		AppProjectMenuItem,
		Fragment,
		AppVerticalMenu,
		TwModal
	},
})
export default class PageParentTask extends Vue {

	@State('selectedProject') selectedProject
	@State('selectedMenu') selectedMenu
	@Action('selectMenu') selectMenu

	public visible: boolean = true
	public favorites: any[] = []

	public show = {
		favoriteActions: false,
		favorites: false,
		workspaceActions: false,
	}

	handleClickFavorite (favorite) {
		this.selectMenu(favorite.menuItemId)
		this.$router.push(`/tasks/${ favorite.menuItemId }`)
	}

	async handleSelectMenuItem (item) {
		await this.selectMenu(item.id)
		await this.$router.push({ name: 'tasks-id', params: { id: item.id }, query: { view: 'list' } })
	}

	async fetch () {
		if (this.$store.getters.activeMenu != null) {
			await this.selectMenu(this.$store.getters.activeMenu)
		}

		this.favorites = await this.$api.favorites.findAllByProjectId(this.selectedProject)

		if (this.$route.params.id == null || this.$route.query.view == null) {
			await this.$router.push(`/tasks/${ this.selectedMenu }?view=list`)
		}
	}

	created () {
		this.$bus.$on('pplanner:update-favorites', async () => {
			this.favorites = await this.$api.favorites.findAllByProjectId(this.selectedProject)
		})
	}
}
</script>
