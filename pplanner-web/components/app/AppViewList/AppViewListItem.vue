<template>
	<div class="mt-4 p-4 border rounded-2xl">
		<div class="flex items-baseline gap-2">
			<div :class="item.status.color" class="font-medium rounded-t-md py-1 px-2 text-white">{{ item.status.name }}</div>
			<div class="text-primary">{{ taskCount }} tâche(s)</div>
		</div>

		<div>
			<draggable :animation="200" :list="item.tasks" group="tasks" @change="handleChangeDragTask">
				<div v-for="task in item.tasks" :key="task.id" class="mb-1 bg-white shadow rounded p-2 text-base">
					{{ task.name }}
				</div>
			</draggable>
		</div>
	</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'nuxt-property-decorator'
import draggable from "vuedraggable";

@Component({
	components: {
		draggable
	}
})
export default class AppViewListItem extends Vue {
	@Prop({ required: true }) item: any;

	get taskCount () {
		return this.item.tasks?.length ?? 0
	}

	async handleChangeDragTask (e) {
		if (e.added) {
			await this.$api.tasks.updateStatus(e.added.element.id, this.item.status.id)
		}
	}
}
</script>

<style>

</style>
