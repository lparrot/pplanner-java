<template>
	<div class="h-full justify-center overflow-x-auto">
		<div class="flex flex-col flex-wrap h-full">
			<div v-for="item in items" :key="item.title" class="flex flex-col h-full items-center justify-start bg-gray-100 rounded-lg px-3 w-72 py-3 column-width rounded mr-4">
				<p :class="item.status.color" class="font-semibold font-sans tracking-wide text-sm w-full px-2 py-1 text-center rounded-lg">{{ `${item.status.name} ( ${item.tasks.length} )` }}</p>

				<draggable :list="item.tasks" class="h-full" group="tasks" @change="handleChangeDragTask($event, item.status.id)">
					<tw-task-card v-for="(task) in item.tasks" :key="task.id" :task="task" class="mt-3 cursor-move"></tw-task-card>

					<div class="mt-2 text-primary cursor-pointer rounded-lg hover:bg-primary-200 px-2 py-1">
						<i class="fas fa-plus mr-1"></i>
						<span>Nouvelle tâche</span>
					</div>
				</draggable>
			</div>
		</div>
	</div>
</template>

<script lang="ts">
import { Component } from 'nuxt-property-decorator'
import draggable from "vuedraggable";
import TwTaskCard from "~/components/shared/TwTaskCard.vue";
import { TasksMixin } from "~/mixins/tasks.mixin";

@Component({
	components: {
		TwTaskCard,
		draggable
	}
})
export default class AppViewKanban extends TasksMixin {
	public items = []

	async fetch () {
		await this.fetchData();
	}

	async handleChangeDragTask (e, statusId) {
		if (e.added) {
			await this.$api.tasks.updateStatus(e.added.element.id, statusId)
		}
	}
}
</script>

<style>

</style>
