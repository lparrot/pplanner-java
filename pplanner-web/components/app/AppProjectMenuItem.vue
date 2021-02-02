<template>
	<div>
		<div :class="{'bg-secondary': selected, 'cursor-pointer': selectable}" class="flex justify-between items-center px-2 py-1 rounded">
			<div class="flex items-center w-full">
				<i :class="[itemIcon, {'text-white': selected}]" class="mr-2" @click="openOrCloseChildren"></i>
				<div :class="{'text-white': selected}" class="text-base font-medium hover:underline w-full" @click="selectable && selectItem(item)">{{ item.name }}</div>
			</div>
			<div v-if="editable" class="flex gap-4">
				<slot name="icons">
					<tw-dropdown ref="addFavoriteDropdown" container-class="w-56" fixed>
						<template #activator>
							<i :class="{'text-white': selected, 'text-gray-400 hover:text-secondary': !selected}" class="flex fas fa-ellipsis-h cursor-pointer"></i>
						</template>

						<div class="p-2">
							<tw-menu>
								<tw-menu-item icon="fas fa-star" label="Ajouter aux favoris" @click="handleClickAddFavorite"></tw-menu-item>
							</tw-menu>
						</div>
					</tw-dropdown>

					<tw-dropdown ref="addItemDropdown" container-class="w-56" fixed>
						<template #activator>
							<i v-if="openable" :class="{'text-white': selected, 'text-gray-400 hover:text-secondary': !selected}" class="flex fas fa-plus-circle cursor-pointer"></i>
						</template>

						<template>
							<div class="p-2">
								<template v-if="item.type === 'WORKSPACE'">
									<div class="font-semibold uppercase text-primary-400 ml-2 mb-2">Options de l'espace de travail</div>
									<tw-menu>
										<tw-menu-item icon="fas fa-folder" label="Nouveau dossier" @click="handleClickAddFolder(item)"></tw-menu-item>
										<tw-menu-item icon="fas fa-list" label="Nouvelle liste" @click="handleClickAddList(item)"></tw-menu-item>
									</tw-menu>
								</template>

								<template v-else>
									<div class="font-semibold uppercase text-primary-400 ml-2 mb-2">Options du dossier</div>
									<tw-menu>
										<tw-menu-item icon="fas fa-list" label="Nouvelle liste" @click="handleClickAddList(item)"></tw-menu-item>
									</tw-menu>
								</template>
							</div>
						</template>
					</tw-dropdown>
				</slot>
			</div>
		</div>
		<transition name="scale-up-ver-top">
			<div v-show="item.opened" class="flex-col ml-8">
				<app-project-menu-item v-for="itemChild in item.children" :key="itemChild.id" :item="itemChild"></app-project-menu-item>
			</div>
		</transition>
	</div>
</template>

<script lang="ts">
import { Action, Component, Getter, Inject, Prop, Provide, Ref, Vue } from "nuxt-property-decorator";
import TwDropdown from "~/components/shared/TwDropdown.vue";
import TwMenu from "~/components/shared/TwMenu.vue";
import TwMenuItem from "~/components/shared/TwMenuItem.vue";
import AppProjectMenuItemContainer from "~/components/app/AppProjectMenuItemContainer.vue";

@Component({
	components: {
		TwDropdown,
		TwMenu,
		TwMenuItem
	},
})
export default class AppProjectMenuItem extends Vue {
	// Injection du parent dans chaque enfant
	@Provide("parent") parentInstance = this
	@Inject({ default: null, from: "container" }) container!: AppProjectMenuItemContainer
	@Inject({ default: null, from: "parent" }) parent!: AppProjectMenuItem

	@Ref('addFavoriteDropdown') addFavoriteDropdown: TwDropdown
	@Ref('addItemDropdown') addItemDropdown: TwDropdown

	@Prop() item: Models.ProjectMenuItem

	@Action('selectMenu') selectMenu
	@Getter('activeMenu') activeMenu

	get itemIcon () {
		switch (this.item.type) {
			case 'WORKSPACE':
				return this.item.opened ? 'fas fa-caret-down' : 'fas fa-caret-right'
			case 'LIST':
				return 'fas fa-list'
			case 'FOLDER':
				return this.item.opened ? 'fas fa-folder-open' : 'fas fa-folder'
			default:
				break;
		}
	}

	get selected () {
		const selected = this.activeMenu === this.item.id
		if (selected && this.parent != null && !this.parent.item.opened) {
			this.parent.open()
		}
		return selected
	}

	get selectable () {
		return this.container?.selectableTypes.indexOf(this.item.type) > -1
	}

	get openable () {
		return this.item.type !== 'LIST'
	}

	get editable () {
		return this.container?.editable ?? false
	}

	created () {
		if (this.item.opened == null) {
			Vue.set(this.item, 'opened', false)
		}

		if (this.container.allOpened) {
			Vue.set(this.item, 'opened', true)
		}
	}

	open () {
		if (this.openable) {
			Vue.set(this.item, 'opened', true)
			if (this.parent != null && !this.parent.item.opened) {
				this.parent.open()
			}
		}
	}

	openOrCloseChildren () {
		if (this.openable) {
			Vue.set(this.item, 'opened', !this.item.opened)
		}
	}

	selectItem (item) {
		if (this.selected && this.$children != null && this.$children.length > 0) {
			this.openOrCloseChildren()
		} else {
			if (this.container != null) {
				this.container.setModel(item)
			} else {
				this.$bus.$emit('pplanner:select-menuitem')
			}
		}
	}

	async handleClickAddFavorite () {
		this.addFavoriteDropdown.hide()
		await this.$api.favorites.create(this.item.id)
		this.$bus.$emit('pplanner:favorites_update')
	}

	async handleClickAddFolder (item) {
		this.addItemDropdown.hide()
		this.$bus.$emit('pplanner:folder_add', item)
	}

	async handleClickAddList (item) {
		this.addItemDropdown.hide()
		this.$bus.$emit('pplanner:list_add', item)
	}
}
</script>
