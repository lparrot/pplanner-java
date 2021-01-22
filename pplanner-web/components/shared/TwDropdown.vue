<template>
	<div v-click-outside="() => visible = false" class="relative inline-block text-left">
		<div class="flex items-center gap-2 cursor-pointer" @click="toggle">
			<div>
				<slot name="activator"></slot>
			</div>
			<i :class="{[iconOpen]: visible, [iconClose]: !visible}"></i>
		</div>
		<transition name="scale-in-ver-top">
			<div v-show="visible" class="origin-top-right absolute left-0 mt-2 w-56 rounded-md shadow-lg bg-white ring-1 ring-black ring-opacity-5">
				<slot>
					<div aria-labelledby="options-menu" aria-orientation="vertical" class="py-1" role="menu">
						<a class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900" role="menuitem">Account settings</a>
						<a class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900" role="menuitem">Support</a>
						<a class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900" role="menuitem">License</a>
						<form action="#" method="POST">
							<button class="block w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 focus:outline-none focus:bg-gray-100 focus:text-gray-900" role="menuitem" type="submit">
								Sign out
							</button>
						</form>
					</div>
				</slot>
			</div>
		</transition>
	</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'nuxt-property-decorator'


@Component({})
export default class TwDropdown extends Vue {
	@Prop({ default: 'fas fa-caret-up' }) iconOpen: string
	@Prop({ default: 'fas fa-caret-down' }) iconClose: string

	visible: boolean = false

	toggle () {
		this.visible = !this.visible
	}
}
</script>

<style>

</style>
