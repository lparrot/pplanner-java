<template>
	<section class="contents">
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
								<div v-for="favorite in favorites" :key="favorite.id" :class="{'text-secondary': activeMenu === favorite.id}" class="flex justify-between items-center cursor-pointer text-primary hover:text-secondary">
									<div class="ml-1 mt-2 hover:underline" @click="handleClickFavorite(favorite)">{{ favorite.name }}</div>
									<tw-dropdown fixed>
										<template #activator>
											<i class="fas fa-ellipsis-h"></i>
										</template>
										<tw-menu class="p-2">
											<tw-menu-item icon="fas fa-trash" label="Supprimer" @click="handleClickDeleteFavorite(favorite)"></tw-menu-item>
										</tw-menu>
									</tw-dropdown>
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

				<app-project-menu-item-container editable @input="handleSelectMenuItem"></app-project-menu-item-container>

				<div class="text-gray-400 hover:text-secondary cursor-pointer mt-4" @click="handleShowModalCreateItem('workspace')">
					<i class="fas fa-plus mr-2"></i>
					<span>Nouvel espace de travail</span>
				</div>
			</div>
		</app-vertical-menu>

		<div class="p-container h-full overflow-auto">
			<nuxt-child :key="$route.fullPath"></nuxt-child>
		</div>

		<validation-observer ref="validator_newWorkspace" tag="form" @submit.prevent="handleSubmitCreateNewWorkspace">
			<template v-if="newItem != null">
				<tw-modal :visible.sync="modals.createNewWorkspace" title="Création d'un nouvel espace de travail">

					<validation-provider #default="{invalid, errors}" name="nom" rules="required">
						<tw-input-text v-model="newItem.name" :error="invalid" :error-message="errors[0]" label="Nom de l'espace de travail" required></tw-input-text>
					</validation-provider>

					<template #actions>
						<button class="p-btn p-btn--primary" type="button" @click="modals.createNewWorkspace = false">Annuler</button>
						<button class="p-btn p-btn--success" type="submit">Créer</button>
					</template>
				</tw-modal>
			</template>
		</validation-observer>

		<validation-observer ref="validator_newFolder" tag="form" @submit.prevent="handleSubmitCreateNewFolder">
			<template v-if="newItem != null">
				<tw-modal :visible.sync="modals.createNewFolder" title="Création d'un nouveau dossier">

					<validation-provider #default="{invalid, errors}" name="nom" rules="required">
						<tw-input-text v-model="newItem.name" :error="invalid" :error-message="errors[0]" label="Nom du dossier" required></tw-input-text>
					</validation-provider>

					<template #actions>
						<button class="p-btn p-btn--primary" type="button" @click="modals.createNewFolder = false">Annuler</button>
						<button class="p-btn p-btn--success" type="submit">Créer</button>
					</template>
				</tw-modal>
			</template>
		</validation-observer>

		<validation-observer ref="validator_newList" tag="form" @submit.prevent="handleSubmitCreateNewList">
			<template v-if="newItem != null">
				<tw-modal :visible.sync="modals.createNewList" title="Création d'une nouvelle liste">

					<validation-provider #default="{invalid, errors}" name="nom" rules="required">
						<tw-input-text v-model="newItem.name" :error="invalid" :error-message="errors[0]" label="Nom de la liste" required></tw-input-text>
					</validation-provider>

					<template #actions>
						<button class="p-btn p-btn--primary" type="button" @click="modals.createNewList = false">Annuler</button>
						<button class="p-btn p-btn--success" type="submit">Créer</button>
					</template>
				</tw-modal>
			</template>
		</validation-observer>
	</section>
</template>

<script lang="ts">
import { Action, Component, Getter, Ref, Vue } from "nuxt-property-decorator";
import AppVerticalMenu from "../components/app/AppVerticalMenu.vue";
import AppProjectMenuItem from "~/components/app/AppProjectMenuItem.vue";
import TwModal from "~/components/shared/TwModal.vue";
import AppProjectMenuItemContainer from "~/components/app/AppProjectMenuItemContainer.vue";
import TwDropdown from "~/components/shared/TwDropdown.vue";
import TwInputText from "~/components/shared/TwInputText.vue";

@Component({
	components: {
		TwInputText,
		AppProjectMenuItemContainer,
		AppProjectMenuItem,
		AppVerticalMenu,
		TwDropdown,
		TwModal,
	},
})
export default class PageParentTask extends Vue {

	@Ref('validator_newWorkspace') validatorNewWorkspace
	@Ref('validator_newFolder') validatorNewFolder
	@Ref('validator_newList') validatorNewList

	@Action('goToTaskIdListPage') goToTaskIdListPage
	@Action('selectMenu') selectMenu
	@Getter('activeProject') activeProject
	@Getter('activeMenu') activeMenu

	public newItem = null
	public favorites: any[] = []
	public visible: boolean = true

	public show = {
		favoriteActions: false,
		favorites: false,
		workspaceActions: false,
	}

	public modals = {
		createNewWorkspace: false,
		createNewFolder: false,
		createNewList: false
	}

	async fetch () {
		this.$bus.$on('pplanner:favorites_update', async () => {
			this.favorites = await this.$api.favorites.findAllByProjectId()
		})

		this.$bus.$on('pplanner:folder_add', async (item) => {
			this.handleShowModalCreateItem('folder', item)
		})

		this.$bus.$on('pplanner:list_add', async (item) => {
			this.handleShowModalCreateItem('list', item)
		})

		if (this.activeMenu != null && this.$route.params.id == null) {
			await this.goToTaskIdListPage()
		}

		this.$bus.$emit('pplanner:favorites_update')
	}

	async handleClickFavorite (favorite) {
		this.selectMenu(favorite.menuItemId)
		await this.goToTaskIdListPage()
	}

	async handleClickDeleteFavorite (favorite) {
		await this.$api.favorites.delete(favorite.id)
		this.$bus.$emit('pplanner:favorites_update')
	}

	async handleSelectMenuItem (item) {
		await this.selectMenu(item.id)
		await this.goToTaskIdListPage()
	}

	handleShowModalCreateItem (type, parent?) {
		this.newItem = {
			projectId: this.activeProject
		}

		if (parent != null) {
			this.newItem.parentId = parent.id
		}

		switch (type) {
			case 'workspace':
				this.validatorNewWorkspace.reset()
				this.modals.createNewWorkspace = true
				break;
			case 'folder':
				this.validatorNewFolder.reset()
				this.modals.createNewFolder = true
				break;
			case 'list':
				this.validatorNewList.reset()
				this.modals.createNewList = true
				break;
			default:
				break;
		}
	}

	/**
	 *
	 */
	async handleSubmitCreateNewWorkspace () {
		const valid = await this.validatorNewWorkspace.validate()
		if (valid) {
			const itemId = await this.$api.items.createItemByType('workspace', this.newItem)
			this.$bus.$emit('pplanner:items_update')
			this.selectMenu(itemId)
			this.modals.createNewWorkspace = false
		}
	}

	/**
	 *
	 */
	async handleSubmitCreateNewFolder () {
		const valid = await this.validatorNewFolder.validate()
		if (valid) {
			const itemId = await this.$api.items.createItemByType('folder', this.newItem)
			this.$bus.$emit('pplanner:items_update')
			this.selectMenu(itemId)
			this.modals.createNewFolder = false
		}
	}

	/**
	 *
	 */
	async handleSubmitCreateNewList () {
		const valid = await this.validatorNewList.validate()
		if (valid) {
			const itemId = await this.$api.items.createItemByType('list', this.newItem)
			this.$bus.$emit('pplanner:items_update')
			this.selectMenu(itemId)
			this.modals.createNewList = false
		}
	}
}
</script>
