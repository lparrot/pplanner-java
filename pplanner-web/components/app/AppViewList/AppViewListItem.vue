<template>
	<div>
		<div class="mt-2 p-4 border rounded-2xl">
			<div class="flex items-baseline gap-2">
				<div :class="item.status.color" class="font-medium rounded-t-md py-1 px-2 text-white">{{ item.status.name }}</div>
				<div class="text-primary">{{ taskCount }} tâche(s)</div>
			</div>

			<div>
				<draggable :animation="200" :list="item.tasks" group="tasks" @change="handleChangeDragTask">
					<div v-for="task in item.tasks" :key="task.id" class="bg-white rounded p-2 text-base shadow-inner">
						{{ task.name }}
					</div>
				</draggable>

				<div class="mt-2 inline-block text-primary cursor-pointer rounded-lg hover:bg-primary-200 px-2 py-1" @click="$bus.$emit('app:add-task-modal', { status: item.status })">
					<i class="fas fa-plus mr-1"></i>
					<span>Nouvelle tâche</span>
				</div>
			</div>
		</div>

		<div class="mt-4 h-8" @mouseenter="show.addNewStatusAction = true" @mouseleave="show.addNewStatusAction = false">
			<transition name="focus-in-contract">
				<div v-show="show.addNewStatusAction" class="flex items-center cursor-pointer font-semibold">
					<div class="flex-grow">
						<hr>
					</div>
					<div class="flex-grow-0 text-secondary mx-4">Ajout d'un statut</div>
					<div class="flex-grow">
						<hr>
					</div>
				</div>
			</transition>
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

	public show = {
		addNewStatusAction: false
	}

	get taskCount () {
		return this.item.tasks?.length ?? 0
	}

	async handleChangeDragTask (e) {
		if (e.added) {
			await this.$api.tasks.updateTaskStatus(e.added.element.id, this.item.status.id)
		}
	}
}
</script>

<style>

</style>
