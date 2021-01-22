<template>
	<div class="flex w-full justify-center items-center h-full">
		<validation-observer ref="validator" class="md:w-1/2 md:mx-0 w-full border border-primary p-6 mx-4 rounded-xl" tag="form" @submit.prevent="handleSubmitRegisterForm">
			<div class="text-3xl">Enregistrement</div>
			<validation-provider #default="{invalid, errors}" name="adresse e-mail" rules="required|email" slim>
				<tw-input-text v-model="form.email" :error="invalid" :error-message="errors[0]" class="my-2" label="Adresse e-mail" required></tw-input-text>
			</validation-provider>
			<validation-provider #default="{invalid, errors}" name="mot de passe" rules="required" slim>
				<tw-input-text v-model="form.password" :error="invalid" :error-message="errors[0]" class="my-2" label="Mot de passe" required type="password"></tw-input-text>
			</validation-provider>
			<validation-provider #default="{invalid, errors}" name="confirmation mot de passe" rules="required" slim>
				<tw-input-text v-model="form.password" :error="invalid" :error-message="errors[0]" class="my-2" label="Confirmation du mot de passe" required type="password"></tw-input-text>
			</validation-provider>
			<div class="flex justify-end">
				<button class="p-btn p-btn--secondary" type="submit">Se connecter</button>
			</div>
		</validation-observer>
	</div>
</template>

<script lang="ts">
import { Component, Ref, Vue } from 'nuxt-property-decorator'
import TwInputText from '~/components/shared/TwInputText.vue'

@Component({
	components: { TwInputText },
	layout: 'landing',
	auth: false,
})
export default class PageLogin extends Vue {
	@Ref('validator') readonly validator: any

	public form = { email: null, password: null }

	async handleSubmitRegisterForm () {
		const valid = await this.validator.validate()
		if (valid) {

		}
	}
}
</script>

<style>

</style>
