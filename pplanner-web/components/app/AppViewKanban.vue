<template>
	<div class="flex justify-center flex-wrap overflow-x-auto">
		<div class="min-h-screen flex overflow-x-scroll py-12">
			<div v-for="item in items" :key="item.title" class="bg-gray-100 rounded-lg px-3 py-3 column-width rounded mr-4">
				<p class="text-gray-700 font-semibold font-sans tracking-wide text-sm">{{ item.status.name }}</p>
				<!-- Draggable component comes from vuedraggable. It provides drag & drop functionality -->
				<draggable :animation="200" :list="item.tasks" ghost-class="ghost-card" group="tasks" @move="handleMoveTask">
					<!-- Each element from here will be draggable and animated. Note :key is very important here to be unique both for draggable and animations to be smooth & consistent. -->
					<tw-task-card v-for="(task) in item.tasks" :key="task.id" :task="task" class="mt-3 cursor-move"></tw-task-card>
					<!-- </transition-group> -->
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
	public statusList: any[] = []

	async fetch () {
		await this.fetchTasks();
		this.statusList = await this.$axios.$get(`/task_status/items/${ this.item.id }`)

		this.items = Object.keys(this.tasks).map(taskByStatus => {
			return {
				status: this.statusList.find(status => taskByStatus === status.id),
				tasks: this.tasks[taskByStatus]
			}
		})
	}

	async handleMoveTask (event) {
		console.log(event);
	}
}
</script>

<style>

</style>
