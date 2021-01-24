<template>
	<div ref="container" class="form-group">
		<label v-if="label != null" :class="{'text-danger':errorMessage}" :for="labelForComputed" class="font-medium mb-2">{{ label }}
			<div v-if="required" class="inline-block text-danger">*</div>
		</label>
		<slot>
			<input :id="'input_' + _uid" v-model="model" class="form-control">
		</slot>
		<div v-if="error" class="text-danger text-sm mt-2">{{ errorMessage }}</div>
	</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'nuxt-property-decorator'
import { VModel } from 'vue-property-decorator'

@Component({})
export default class TwInputText extends Vue {

	@Prop() readonly error!: boolean
	@Prop() readonly errorMessage!: string
	@Prop() readonly inputAttrs!: string
	@Prop() readonly label!: string
	@Prop() readonly labelFor!: string
	@Prop({ type: Boolean }) readonly required!: boolean

	@VModel() readonly model!: any

	get labelForComputed () {
		return this.labelFor ?? 'input_' + this['_uid']
	}
}
</script>

<style>

</style>
