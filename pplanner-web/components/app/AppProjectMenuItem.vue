<template>
	<fragment>
		<div :class="{'bg-secondary': selected}" class="flex justify-between items-center cursor-pointer px-2 py-1 rounded">
			<div class="flex items-center">
				<i :class="[itemIcon, {'text-white': selected}]" class="mr-2" @click="openOrCloseChildren"></i>
				<div :class="{'text-white': selected}" class="text-base font-medium hover:underline" @click="selectItem(item)">{{ item.name }}</div>
			</div>
			<div class="flex gap-4">
				<slot name="icons">
					<tw-dropdown width="w-56">
						<template #activator>
							<i :class="{'text-white': selected, 'text-gray-400 hover:text-secondary': !selected}" class="flex fas fa-ellipsis-h cursor-pointer"></i>
						</template>

						<tw-menu class="p-2">
							<tw-menu-item icon="fas fa-star" label="Ajouter aux favoris" @click="handleClickAddFavorite"></tw-menu-item>
						</tw-menu>
					</tw-dropdown>
					<i v-if="openable" :class="{'text-white': selected, 'text-gray-400 hover:text-secondary': !selected}" class="flex fas fa-plus-circle cursor-pointer"></i>
				</slot>
			</div>
		</div>
		<transition name="scale-up-ver-top">
			<div v-show="item.opened" class="flex-col ml-8">
				<app-project-menu-item v-for="itemChild in item.children" :key="itemChild.id" :item="itemChild"></app-project-menu-item>
			</div>
		</transition>
	</fragment>
</template>

<script lang="ts">
import { Action, Component, Inject, Prop, Provide, State, Vue } from "nuxt-property-decorator";
import { Fragment } from 'vue-fragment'
import TwDropdown from "~/components/shared/TwDropdown.vue";
import TwMenu from "~/components/shared/TwMenu.vue";
import TwMenuItem from "~/components/shared/TwMenuItem.vue";
import AppProjectMenuItemContainer from "~/components/app/AppProjectMenuItemContainer.vue";

@Component({
	components: {
		Fragment,
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

	@Prop() item: Models.ProjectMenuItem

	@Action('selectMenu') selectMenu
	@State('selectedMenu') selectedMenu

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
		const selected = this.selectedMenu === this.item.id
		if (selected && this.parent != null && !this.parent.item.opened) {
			this.parent.open()
		}
		return selected
	}

	get openable () {
		return this.item.type !== 'LIST'
	}

	created () {
		if (this.item.opened == null) {
			Vue.set(this.item, 'opened', false)
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
		if (this.container != null) {
			this.container.setModel(item)
		} else {
			this.$bus.$emit('pplanner:select-menuitem')
		}
	}

	async handleClickAddFavorite () {
		await this.$api.favorites.create(this.item.id)
		this.$bus.$emit('pplanner:update-favorites')
	}
}
</script>
