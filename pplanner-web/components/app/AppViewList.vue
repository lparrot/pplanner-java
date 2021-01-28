<template>
	<div class="flex flex-col p-4 h-full">
		<div class="flex flex-col">
			<!-- Entête -->
			<div class="flex justify-between items-center text-primary">
				<div class="flex items-center">
					<div class="mr-2">
						<span>{{ taskCount }} tâche(s) actives</span>
					</div>
					<tw-dropdown>
						<template #activator>
							<i class="fas fa-filter"></i>
						</template>
					</tw-dropdown>
					<input class="ml-2 w-48 bg-transparent focus:outline-none" placeholder="Filtrer par nom de tâche ..." type="text">
				</div>

				<div class="flex gap-4 items-center">
					<tw-dropdown>
						<template #activator>
							<span>Regrouper par</span>
						</template>
					</tw-dropdown>
					<tw-dropdown>
						<template #activator>
							<span>Sous-tâches</span>
						</template>
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
			<app-view-list-container :menu-item="item" :tasks-by-status="tasks"></app-view-list-container>
		</div>
	</div>
</template>

<script lang="ts">
import { Component } from 'nuxt-property-decorator'
import { TasksMixin } from "~/mixins/tasks.mixin";
import AppViewListContainer from "~/components/app/AppViewList/AppViewListContainer.vue";

@Component({
	components: {
		AppViewListContainer
	}
})
export default class AppViewList extends TasksMixin {
	get taskCount () {
		if (this.tasks == null) {
			return 0
		}

		let count = 0
		Object.keys(this.tasks).forEach(key => count += this.tasks[key].length)
		return count
	}

	async fetch () {
		await this.fetchTasks()
	}
}
</script>

<style>

</style>
