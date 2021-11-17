<template>
	<div class="flex justify-center items-center">
		<div class="w-full max-w-2xl mx-auto rounded-lg dark:bg-primary-800">
			<div class="px-6 py-4">
				<h2 class="text-3xl font-bold text-center text-primary-700 dark:text-white">PPLANNER</h2>

				<h3 class="mt-1 text-xl font-medium text-center text-primary-600 dark:text-primary-200">Ecran de connexion</h3>

				<p class="mt-1 text-center text-primary-500 dark:text-primary-400">Connectez-vous ou créez un nouveau compte</p>

				<validation-observer ref="validator" class="md:w-1/2 md:mx-0 w-full border border-primary p-6 mx-4 rounded-xl" novalidate tag="form" @submit.prevent="handleSubmitLoginForm">
					<div class="w-full mt-4">
						<validation-provider ref="systemErrorValidator" #default="{errors}">
							<small v-if="errors" class="text-sm text-danger">{{ errors[0] }}</small>
						</validation-provider>
					</div>

					<div class="w-full mt-4">
						<validation-provider #default="{invalid, errors}" name="adresse e-mail" rules="required|email" slim>
							<tw-input-text :error="invalid" :error-message="errors[0]" class="my-2" label="Adresse e-mail" label-for="input_email" required>
								<input id="input_email" v-model="form.username" aria-label="Adresse e-mail"
											 class="block w-full px-4 py-2 mt-2 text-primary-700 placeholder-primary-500 bg-white border rounded-md dark:bg-primary-800 dark:border-primary-600 dark:placeholder-primary-400 focus:border-secondary-500 dark:focus:border-secondary-500 focus:outline-none focus:ring"
											 placeholder="Adresse e-mail" type="email"/>
							</tw-input-text>
						</validation-provider>
					</div>

					<div class="w-full mt-4">

						<validation-provider #default="{invalid, errors}" name="mot de passe" rules="required" slim>
							<tw-input-text :error="invalid" :error-message="errors[0]" class="my-2" label="Mot de passe" label-for="input_password" required>
								<input id="imput_password" v-model="form.password" aria-label="Mot de passe"
											 class="block w-full px-4 py-2 mt-2 text-primary-700 placeholder-primary-500 bg-white border rounded-md dark:bg-primary-800 dark:border-primary-600 dark:placeholder-primary-400 focus:border-secondary-500 dark:focus:border-secondary-500 focus:outline-none focus:ring"
											 placeholder="Mot de passe" type="password"/>
							</tw-input-text>
						</validation-provider>
					</div>

					<div class="flex items-center justify-between mt-4">
						<a class="text-sm text-primary-600 dark:text-primary-200 hover:text-primary-500" href="#">Mot de passe oublié ?</a>

						<button class="px-4 py-2 leading-5 text-white transition-colors duration-200 transform bg-primary-700 rounded hover:bg-primary-600 focus:outline-none" type="submit">Connexion</button>
					</div>
				</validation-observer>
			</div>

			<div class="flex items-center justify-center py-4 text-center bg-primary-50 dark:bg-primary-700">
				<span class="text-sm text-primary-600 dark:text-primary-200">Vous n'avez pas de compte ? </span>

				<nuxt-link class="mx-2 text-sm font-bold text-secondary-500 dark:text-secondary-400 hover:underline" to="/register">S'enregistrer</nuxt-link>
			</div>
		</div>
	</div>
</template>

<script lang="ts">
import { Component, Getter, Ref, Vue } from 'nuxt-property-decorator'
import TwInputText from '~/components/shared/TwInputText.vue'

@Component({
	components: { TwInputText },
	layout: 'landing',
	auth: false,
})
export default class PageLogin extends Vue {
	@Ref('validator') readonly validator: any
	@Ref('systemErrorValidator') readonly systemErrorValidator: any

	@Getter('activeProject') activeProject

	public form = { username: 'kestounet@gmail.com', password: '123' }

	async handleSubmitLoginForm () {
		const valid = await this.validator.validate()
		if (valid) {
			try {
				await this.$auth.loginWith('local', { data: this.form })

				// TODO : A supprimer à terme
				if (this.activeProject == null) {
					await this.$store.dispatch('selectFirstProject')
				}

				await this.$router.push({ name: 'tasks' })
			} catch (err) {
				if (err?.response?.data?.message != null) {
					this.systemErrorValidator.setErrors([ err?.response?.data?.message ])
				}
			}
		}
	}
}
</script>

<style>

</style>
