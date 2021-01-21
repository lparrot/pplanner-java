<template>
	<fragment>
		<div :class="{'bg-primary text-white': selected}"
				 class="flex justify-between items-center cursor-pointer px-2 py-1 rounded">
			<div class="flex items-center">
				<i :class="itemIcon" class="mr-2" @click="openOrCloseChildren"></i>
				<div class="text-base font-medium hover:underline" @click="selectItem(item)">{{ item.name }}</div>
			</div>
			<div class="flex gap-4">
				<i :class="{'text-white': selected, 'text-gray-400 hover:text-secondary': !selected}"
					 class="flex fas fa-ellipsis-h cursor-pointer"></i>
				<i v-if="openable" :class="{'text-white': selected, 'text-gray-400 hover:text-secondary': !selected}"
					 class="flex fas fa-plus-circle cursor-pointer"></i>
			</div>
		</div>
		<transition name="scale-up-ver-top">
			<div v-show="item.opened" class="flex-col ml-8">
				<slot></slot>
			</div>
		</transition>
	</fragment>
</template>

<script lang="ts">
import {Action, Component, Getter, Inject, Prop, Provide, Vue} from "nuxt-property-decorator";
import {Fragment} from 'vue-fragment'

@Component({
	components: {
		Fragment,
	},
})
export default class PProjectMenuItem extends Vue {
	// Injection du parent dans chaque enfant
	@Provide("parent") parentInstance = this
	@Inject({default: null, from: "parent"}) parent!: PProjectMenuItem

	@Prop() item: Models.ProjectMenuItem

	@Getter('activeListItem') activeListItem
	@Action('selectProjectItem') selectProjectItem

	get itemIcon() {
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

	get selected() {
		const selected = this.activeListItem === this.item.id
		if (selected && this.parent != null && !this.parent.item.opened) {
			this.parent.open()
		}
		return selected
	}

	get openable() {
		return this.item.type !== 'LIST'
	}

	created() {
		if (this.item.opened == null) {
			Vue.set(this.item, 'opened', false)
		}
	}

	open() {
		if (this.openable) {
			Vue.set(this.item, 'opened', true)
			if (this.parent != null && !this.parent.item.opened) {
				this.parent.open()
			}
		}
	}

	openOrCloseChildren() {
		if (this.openable) {
			Vue.set(this.item, 'opened', !this.item.opened)
		}
	}

	selectItem(item) {
		this.selectProjectItem(item.id)
		this.$router.push(`/tasks/${item.id}`)
	}
}
</script>
