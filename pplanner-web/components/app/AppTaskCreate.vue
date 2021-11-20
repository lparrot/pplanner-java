<template>
	<div>
		<div class="grid grid-cols-2 gap-2">
			<validation-provider #default="{invalid, errors}" name="nom de la tâche" rules="required" slim>
				<tw-input-text :error="invalid" :error-message="errors[0]" label="Créer la tâche" label-for="input_name" required>
					<input id="input_name" v-model="task.name" class="form-control" placeholder="Nom de la tache ..." type="text">
				</tw-input-text>
			</validation-provider>
			<validation-provider ref="status_validator" #default="{invalid, errors}" name="statut" rules="required" slim>
				<tw-input-text :error="invalid" :error-message="errors[0]" label="Statut" label-for="input_status" required>
					<tw-dropdown ref="status_dropdown" container-class="w-full" label="Statut">
						<template #activator>
							<input id="input_status" :value="task?.status?.name" class="w-full form-control" readonly type="text">
						</template>
						<div class="p-2">
							<tw-menu>
								<div v-for="status in statusList" :key="status.id" :class="['px-2 rounded flex items-center hover:bg-primary-100', task?.status?.id === status?.id && 'bg-primary-300']" @click="handleSelectStatus(status)">
									<div :class="['w-4 h-4', status.color]"></div>
									<tw-menu-item :label="status.name"></tw-menu-item>
								</div>
							</tw-menu>
						</div>
					</tw-dropdown>
				</tw-input-text>
			</validation-provider>
			<validation-provider ref="task_item_validator" #default="{invalid, errors}" name="emplacement de la tâche" rules="required" slim>
				<tw-input-text :error="invalid" :error-message="errors[0]" label="Dans le menu" label-for="input_menu" required>
					<tw-dropdown ref="task_item_dropdown" container-class="w-full" label="Dans le menu">
						<template #activator>
							<input id="input_menu" :value="task?.item?.name" class="w-full form-control" readonly type="text">
						</template>
						<app-project-menu-item-container v-model="task.item" :editable="false" :selectable-types="['LIST']" all-opened class="p-4 max-h-72 overflow-y-auto overflow-x-hidden" @input="handleSelectMenuItemTaskCreate"></app-project-menu-item-container>
					</tw-dropdown>
				</tw-input-text>
			</validation-provider>
			<validation-provider #default="{invalid, errors}" name="description de la tâche" rules="required" slim>
				<tw-input-text :error="invalid" :error-message="errors[0]" class="col-span-2" label="Description de la tâche" label-for="input_description" required>
					<textarea id="input_description" v-model="task.description" class="form-control" placeholder="Description de la tache ..."></textarea>
				</tw-input-text>
			</validation-provider>
		</div>
	</div>
</template>

<script lang="ts">
import { Component, Ref, State, Vue, Watch } from 'nuxt-property-decorator'
import { VModel } from "vue-property-decorator";
import AppProjectMenuItemContainer from "~/components/app/AppProjectMenuItemContainer.vue";
import TwInputText from "~/components/shared/TwInputText.vue";
import TwDropdown from "~/components/shared/TwDropdown.vue";

@Component({
	components: { AppProjectMenuItemContainer, TwDropdown, TwInputText },
})
export default class AppTaskCreate extends Vue {
	@Ref('task_item_validator') taskItemValidator
	@Ref('status_validator') statusValidator
	@Ref('task_item_dropdown') taskItemDropdown: TwDropdown
	@Ref('status_dropdown') statusDropdown: TwDropdown

	@State(state => state['task-view'].statusList) statusList!: any[]

	@VModel() task: any

	async handleSelectMenuItemTaskCreate () {
		this.taskItemDropdown.hide()
	}

	async handleSelectStatus (status) {
		const valid = await this.statusValidator.validate(status)
		if (valid) {
			this.task.status = status
			this.statusDropdown.hide()
		}
	}

	@Watch("task", { immediate: true, deep: true })
	async onTaskChanged (task: any) {
		this.$nextTick(async () => {
			await this.taskItemDropdown.hide()
		})
	}

}
</script>

<style>

</style>
