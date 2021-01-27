<template>
	<div>
		<div class="grid grid-cols-2 gap-2">
			<validation-provider #default="{invalid, errors}" name="nom de la tâche" rules="required" slim>
				<tw-input-text :error="invalid" :error-message="errors[0]" label="Créer la tâche" label-for="input_name" required>
					<input id="input_name" v-model="task.name" class="form-control" placeholder="Nom de la tache ..." type="text">
				</tw-input-text>
			</validation-provider>
			<validation-provider ref="task_item_validator" #default="{invalid, errors, validate}" name="emplacement de la tâche" rules="required" slim>
				<tw-dropdown ref="task_item_dropdown" container-class="w-full" label="Dans le menu">
					<template #activator>
						<tw-input-text :error="invalid" :error-message="errors[0]" label="Dans le menu" label-for="input_menu" required>
							<input id="input_menu" :value="taskEditName" class="form-control" readonly type="text">
						</tw-input-text>
					</template>
					<template #default="{hide}">
						<app-project-menu-item-container v-model="task.item" :editable="false" :selectable-types="['LIST']" all-opened class="p-4 max-h-72 overflow-y-auto overflow-x-hidden" @input="handleSelectMenuItemTaskCreate($event, validate, hide)"></app-project-menu-item-container>
					</template>
				</tw-dropdown>
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
import { Component, Ref, Vue, Watch } from 'nuxt-property-decorator'
import { VModel } from "vue-property-decorator";
import AppProjectMenuItemContainer from "~/components/app/AppProjectMenuItemContainer.vue";
import TwInputText from "~/components/shared/TwInputText.vue";
import TwDropdown from "~/components/shared/TwDropdown.vue";

@Component({
	components: { AppProjectMenuItemContainer, TwDropdown, TwInputText },
})
export default class AppTaskCreate extends Vue {
	@Ref('task_item_validator') taskItemValidator
	@Ref('task_item_dropdown') taskItemDropdown: TwDropdown

	@VModel() task: any

	get taskEditName () {
		return this.task?.item?.name
	}

	async handleSelectMenuItemTaskCreate (event, validate, hide) {
		await validate(event);
		hide()
	}

	@Watch("task", { immediate: true, deep: true })
	async onTaskChanged (val: any, oldVal: any) {
		this.$nextTick(async () => {
			await this.taskItemValidator.validate(val)
			await this.taskItemDropdown.hide()
		})
	}

}
</script>

<style>

</style>
