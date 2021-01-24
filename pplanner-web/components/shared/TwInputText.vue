<template>
	<div ref="container" class="form-group">
		<label v-if="label != null" :class="{'text-danger':errorMessage}" :for="d_labelFor" class="font-medium mb-2">{{ label }}
			<div v-if="required" class="inline-block text-danger">*</div>
		</label>
		<slot>
			<input :id="'input_' + _uid" v-model="model" v-bind="$attrs" class="form-control">
		</slot>
		<div v-if="error" class="text-danger text-sm mt-2">{{ errorMessage }}</div>
	</div>
</template>

<script lang="ts">
import { Component, Prop, Ref, Vue } from 'nuxt-property-decorator'
import { VModel } from 'vue-property-decorator'

@Component({})
export default class TwInputText extends Vue {

	@Prop() readonly error: boolean
	@Prop() readonly errorMessage: string
	@Prop() readonly label!: string
	@Prop() readonly labelFor!: string
	@Prop({ type: Boolean }) readonly required!: boolean

	@Ref('container') container!: HTMLDivElement

	@VModel() readonly model!: any

	public d_labelFor = null

	mounted () {
		this.$nextTick(() => {
			if (this.labelFor != null) {
				this.d_labelFor = this.labelFor
			} else {
				const input = this.container && this.container.querySelector('.form-control')
				this.d_labelFor = input.id
			}
		})
	}

}
</script>

<style>

</style>
