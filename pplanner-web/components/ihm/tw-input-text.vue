<template>
	<div class="flex flex-col w-full">
		<label v-if="label != null" :for="'input_' + _uid" class="font-medium mb-2">{{ label }}
			<div v-if="requiredField" class="inline-block text-danger">*</div>
		</label>
		<input :id="'input_' + _uid" v-model="model" v-bind="$attrs" class="py-2 px-3 rounded bg-white border border-opacity-75 border-primary focus:outline-none">
		<div v-if="error" class="text-danger text-sm mt-2">{{ errorMessage }}</div>
	</div>
</template>

<script lang="ts">
import {Component, Prop, Vue} from 'nuxt-property-decorator'
import {VModel} from 'vue-property-decorator'

@Component({})
export default class TwInputText extends Vue {

	@Prop() readonly error: boolean
	@Prop() readonly errorMessage: string
	@Prop() readonly inputOptions!: any
	@Prop() readonly label!: string
	@Prop({type: Boolean}) readonly requiredField!: boolean

	@VModel() readonly model!: any

}
</script>

<style>

</style>
