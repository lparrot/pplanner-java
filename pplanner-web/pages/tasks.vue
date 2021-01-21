<template>
	<fragment>
		<p-vertical-menu :visible.sync="visible">
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
								<div v-for="favorite in favorites" :key="favorite.id" :class="{'text-secondary': activeListItem === favorite.id}" class="flex justify-between items-center cursor-pointer text-primary hover:text-secondary">
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
				<p-project-menu-item v-for="workspace in workspaces" :key="workspace.id" :item="workspace">
					<p-project-menu-item v-for="item in workspace.children" :key="item.id" :item="item">
						<template v-if="item.children">
							<p-project-menu-item v-for="itemChild in item.children" :key="itemChild.id"
																	 :item="itemChild"></p-project-menu-item>
						</template>
					</p-project-menu-item>
				</p-project-menu-item>

				<div class="text-gray-400 hover:text-secondary cursor-pointer mt-4">
					<i class="fas fa-plus mr-2"></i>
					<span>Nouvel espace de travail</span>
				</div>
			</div>
		</p-vertical-menu>

		<div class="p-container h-full">
			<nuxt-child></nuxt-child>
		</div>
	</fragment>
</template>

<script lang="ts">
import {Action, Component, Getter, State, Vue} from "nuxt-property-decorator";
import PVerticalMenu from "../components/PVerticalMenu.vue";
import {Fragment} from 'vue-fragment'
import PProjectMenuItem from "~/components/PProjectMenuItem.vue";
import {Context} from "@nuxt/types";

@Component({
	components: {
		PProjectMenuItem,
		Fragment,
		PVerticalMenu,
	},
})
export default class PageParentTask extends Vue {

	@State('selectedProject') selectedProject
	@Getter('activeListItem') activeListItem
	@Action('selectProjectItem') selectProjectItem

	public visible: boolean = true
	public favorites: any[] = []
	public workspaces: Models.ProjectMenuItem[] = []

	public show = {
		favoriteActions: false,
		favorites: false,
		workspaceActions: false,
	}

	handleClickFavorite(favorite) {
		this.selectProjectItem(favorite.id)
		this.$router.push(`/tasks/${favorite.id}`)
	}

	async asyncData(ctx: Context) {
		return {
			workspaces: await ctx.$api.projects.findAllWorkspaceByProjectId(ctx.store.state.selectedProject),
			favorites: ctx.$api.favorites.findAllByProjectId(ctx.store.state.selectedProject),
		}
	}
}
</script>
