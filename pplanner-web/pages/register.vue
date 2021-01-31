<template>
	<div class="flex w-full justify-center items-center h-full">
		<validation-observer ref="validator" class="md:w-1/2 md:mx-0 w-full border border-primary p-6 mx-4 rounded-xl" tag="form" @submit.prevent="handleSubmitRegisterForm">
			<div class="text-3xl">Enregistrement</div>
			<validation-provider #default="{invalid, errors}" name="adresse e-mail" rules="required|email" slim>
				<tw-input-text v-model="form.email" :error="invalid" :error-message="errors[0]" class="my-2" label="Adresse e-mail" required></tw-input-text>
			</validation-provider>
			<validation-provider #default="{invalid, errors}" name="mot de passe" rules="required|confirmed:confirmation" slim>
				<tw-input-text :error="invalid" :error-message="errors[0]" class="my-2" label="Mot de passe" label-for="input_password" required>
					<input id="input_password" v-model="form.password" class="form-control" type="password">
				</tw-input-text>
			</validation-provider>
			<validation-provider #default="{invalid, errors}" name="confirmation mot de passe" rules="required" slim vid="confirmation">
				<tw-input-text :error="invalid" :error-message="errors[0]" class="my-2" label="Confirmation du mot de passe" label-for="input_confirm_password" required>
					<input id="input_confirm_password" v-model="form.confirm_password" class="form-control" type="password">
				</tw-input-text>
			</validation-provider>
			<validation-provider #default="{invalid, errors}" name="nom" rules="required" slim>
				<tw-input-text v-model="form.lastname" :error="invalid" :error-message="errors[0]" class="my-2" label="Nom" required></tw-input-text>
			</validation-provider>
			<validation-provider #default="{invalid, errors}" name="prénom" rules="required" slim>
				<tw-input-text v-model="form.firstname" :error="invalid" :error-message="errors[0]" class="my-2" label="Prénom" required></tw-input-text>
			</validation-provider>
			<validation-provider #default="{invalid, errors}" name="emploi" rules="required" slim>
				<tw-input-text v-model="form.job" :error="invalid" :error-message="errors[0]" class="my-2" label="Emploi" required></tw-input-text>
			</validation-provider>
			<div class="flex justify-end">
				<button class="p-btn p-btn--secondary" type="submit">Créer son compte</button>
			</div>
		</validation-observer>
	</div>
</template>

<script lang="ts">
import { Component, Ref, Vue } from 'nuxt-property-decorator'
import TwInputText from '~/components/shared/TwInputText.vue'

type RegisterForm = {
	username: string
	password: string
	confirm_password: string
	lastname: string
	firstname: string
	job: string
}

@Component({
	components: { TwInputText },
	layout: 'landing',
	auth: false,
})
export default class PageLogin extends Vue {
	@Ref('validator') readonly validator: any

	public form: Partial<RegisterForm> = {}

	async handleSubmitRegisterForm () {
		const valid = await this.validator.validate()
		if (valid) {
			try {
				delete this.form.confirm_password
				const res = await this.$api.auth.createAccount(this.form)
				await this.$auth.setUserToken(res)
				await this.$auth.fetchUser()
				await this.$router.push({ name: 'tasks' })
			} catch (err) {
				// TODO : log erreur
			}
		}
	}
}
</script>

<style>

</style>
