<template>
	<div class="mt-4 p-4 border rounded-2xl">
		<div v-if="status != null" class="flex items-baseline gap-2">
			<div :class="status.color" class="font-medium rounded-t-md py-1 px-2 text-white">{{ status.name }}</div>
			<div class="text-primary">{{ tasks.length }} tâche(s)</div>
		</div>

		<div>
			<draggable :animation="200" :list="tasks" group="tasks" @change="handleChangeDragTask">
				<div v-for="task in tasks" :key="task.id" class="mb-1 bg-white shadow rounded p-2 text-base">
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
	@Prop({ required: true }) statusList: any[];
	@Prop({ required: true }) tasks: any[];
	@Prop({ required: true }) statusId: any;

	public test = null

	get status () {
		return this.statusList.find(status => this.statusId === status.id)
	}

	async handleChangeDragTask (e) {
		if (e.added) {
			await this.$axios.$put(`/tasks/${ e.added.element.id }/status/${ this.statusId }`)
		}
	}
}
</script>

<style>

</style>
