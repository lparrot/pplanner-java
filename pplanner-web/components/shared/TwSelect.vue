<template>
	<div v-click-outside="() => visible = false">
		<label v-if="label != null" id="listbox-label" class="block text-sm font-medium text-primary-700" @click="handleClickLabel">
			{{ label }}
		</label>
		<div class="mt-1 relative" @click="toggle">
			<button ref="activator" aria-expanded="true" aria-haspopup="listbox" aria-labelledby="listbox-label" class="relative w-full bg-white border border-primary-300 rounded-md shadow-sm pl-3 pr-10 py-2 text-left cursor-default cursor-pointer focus:outline-none focus:border-secondary sm:text-sm" type="button">
      <span class="flex items-center">
        <img alt="" class="flex-shrink-0 h-6 w-6 rounded-full" src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80">
        <span class="ml-3 block truncate">
          Tom Cook
        </span>
      </span>
				<span class="ml-3 absolute inset-y-0 right-0 flex items-center pr-2 pointer-events-none">
        <svg aria-hidden="true" class="h-5 w-5 text-primary-400" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
          <path clip-rule="evenodd" d="M10 3a1 1 0 01.707.293l3 3a1 1 0 01-1.414 1.414L10 5.414 7.707 7.707a1 1 0 01-1.414-1.414l3-3A1 1 0 0110 3zm-3.707 9.293a1 1 0 011.414 0L10 14.586l2.293-2.293a1 1 0 011.414 1.414l-3 3a1 1 0 01-1.414 0l-3-3a1 1 0 010-1.414z" fill-rule="evenodd"/>
        </svg>
      </span>
			</button>

			<div v-show="visible" class="absolute mt-1 w-full rounded-md bg-white shadow-lg">
				<ul aria-activedescendant="listbox-item-3" aria-labelledby="listbox-label" class="max-h-56 rounded-md py-1 text-base ring-1 ring-black ring-opacity-5 overflow-auto focus:outline-none sm:text-sm" role="listbox" tabindex="-1">
					<li id="listbox-item-0" class="text-primary-900 hover:bg-primary-100 cursor-pointer select-none relative py-2 pl-3 pr-9" role="option">
						<div class="flex items-center">
							<img alt="" class="flex-shrink-0 h-6 w-6 rounded-full" src="https://images.unsplash.com/photo-1491528323818-fdd1faba62cc?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=facearea&amp;facepad=2&amp;w=256&amp;h=256&amp;q=80">
							<span class="ml-3 block font-normal truncate">
              Wade Cooper
            </span>
						</div>

						<span class="absolute inset-y-0 right-0 flex items-center pr-4">
            <svg aria-hidden="true" class="h-5 w-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
              <path clip-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" fill-rule="evenodd"/>
            </svg>
          </span>
					</li>
				</ul>
			</div>
		</div>
	</div>
</template>

<script lang="ts">
import { Component, Prop, Ref, Vue } from 'nuxt-property-decorator'

@Component({})
export default class TwSelect extends Vue {
	@Prop() label: string
	@Ref('activator') activator!: HTMLButtonElement

	public visible = false

	handleClickLabel () {
		this.activator.focus()
		this.visible = true
	}

	toggle () {
		this.visible = !this.visible
	}
}
</script>

<style>

</style>
