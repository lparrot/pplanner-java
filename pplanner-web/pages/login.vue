<template>
	<div class="flex w-full justify-center items-center h-full">
		<validation-observer ref="validator" class="md:w-1/2 md:mx-0 w-full border border-primary p-6 mx-4 rounded-xl" tag="form" @submit.prevent="handleSubmitLoginForm">
			<div class="text-3xl">Connexion</div>
			<validation-provider #default="{invalid, errors}" name="adresse e-mail" rules="required|email" slim>
				<tw-input-text v-model="form.username" :error="invalid" :error-message="errors[0]" class="my-2" label="Adresse e-mail" required-field></tw-input-text>
			</validation-provider>
			<validation-provider #default="{invalid, errors}" name="mot de passe" rules="required" slim>
				<tw-input-text v-model="form.password" :error="invalid" :error-message="errors[0]" class="my-2" label="Mot de passe" required-field type="password"></tw-input-text>
			</validation-provider>
			<div class="flex justify-end">
				<button class="p-btn p-btn--secondary" type="submit">Se connecter</button>
			</div>
		</validation-observer>
	</div>
</template>

<script lang="ts">
import {Component, Ref, Vue} from 'nuxt-property-decorator'
import TwInputText from '~/components/ihm/tw-input-text.vue'

@Component({
	components: {TwInputText},
	layout: 'not-logged',
	auth: false,
})
export default class PageLogin extends Vue {
	@Ref('validator') readonly validator: any

	public form = {username: null, password: null}

	async handleSubmitLoginForm() {
		const valid = await this.validator.validate()
		if (valid) {
			try {
				const res = await this.$auth.loginWith('local', {data: this.form})
				console.log(res);
			} catch (err) {
				console.log(err);
			}
		}
	}
}
</script>

<style>

</style>
