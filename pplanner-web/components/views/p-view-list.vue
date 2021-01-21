<template>
	<div class="flex flex-col p-4 h-full">
		<div class="flex flex-col">
			<!-- Entête -->
			<div class="flex justify-between items-center text-primary">
				<div class="flex items-center">
					<tw-dropdown>
						<i class="fas fa-filter"></i>
					</tw-dropdown>
					<input class="ml-2 w-48 bg-transparent focus:outline-none" placeholder="Filtrer par nom de tâche ..."
								 type="text">
				</div>

				<div class="flex gap-4 items-center">
					<tw-dropdown>
						<span>Regrouper par</span>
					</tw-dropdown>
					<tw-dropdown>
						<span>Sous-tâches</span>
					</tw-dropdown>
					<div class="cursor-pointer px-2 py-1 rounded border border-primary-400 hover:bg-primary-200">
						<i class="fas fa-share-alt mr-2"></i>
						<span>Partager</span>
					</div>
					<i class="fas fa-ellipsis-h"></i>
				</div>
			</div>
		</div>
		<div class="flex flex-col overflow-auto">
			<!-- Contenu -->

		</div>
	</div>
</template>

<script lang="ts">
import {Component, Vue} from 'nuxt-property-decorator'
import {VModel} from "vue-property-decorator";

@Component({})
export default class PViewList extends Vue {
	@VModel() item: any
}
</script>

<style>

</style>
