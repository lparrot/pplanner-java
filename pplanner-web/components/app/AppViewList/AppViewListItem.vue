<template>
	<div class="mt-4 p-4 border rounded-2xl">
		<div class="flex items-baseline gap-2">
			<div :class="[`bg-${status.color}-500`]" class="font-medium rounded-t-md py-1 px-2 text-white">{{ status.name }}</div>
			<div class="text-primary">{{ tasks.length }} tâche(s)</div>
		</div>

		<div>
			<div v-for="task in tasks" :key="task.id" class="mb-1 bg-white shadow rounded p-2 text-base">
				{{ task.name }}
			</div>
		</div>
	</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'nuxt-property-decorator'

@Component({})
export default class AppViewListItem extends Vue {
	@Prop({ required: true }) statusList: any[];
	@Prop({ required: true }) tasks: any[];
	@Prop({ required: true }) statusId: any;

	get status () {
		console.log(this.statusList);
		console.log(this.statusId);
		return this.statusList.find(status => this.statusId === status.id)
	}
}
</script>

<style>

</style>
