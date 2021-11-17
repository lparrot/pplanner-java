<template>
	<div class="flex justify-center items-center">
		<div class="w-full max-w-lg mx-auto rounded-lg dark:bg-gray-800">
			<div class="px-6 py-4">
				<h2 class="text-3xl font-bold text-center text-gray-700 dark:text-white">PPLANNER</h2>

				<h3 class="mt-1 text-xl font-medium text-center text-gray-600 dark:text-gray-200">Ecran de création de compte</h3>

				<p class="mt-1 text-center text-gray-500 dark:text-gray-400">Créez un nouveau compte</p>

				<validation-observer ref="validator" class="md:w-1/2 md:mx-0 w-full border border-primary p-6 mx-4 rounded-xl" novalidate tag="form" @submit.prevent="handleSubmitRegisterForm">
					<div class="w-full mt-4">
						<validation-provider ref="systemErrorValidator" #default="{errors}">
							<small v-if="errors" class="text-sm text-danger">{{ errors[0] }}</small>
						</validation-provider>
					</div>

					<div class="w-full mt-4">
						<validation-provider #default="{invalid, errors}" name="adresse e-mail" rules="required|email" slim>
							<tw-input-text :error="invalid" :error-message="errors[0]" class="my-2" label="Adresse e-mail" label-for="input_email" required>
								<input id="input_email" v-model="form.username" aria-label="Adresse e-mail"
											 class="block w-full px-4 py-2 mt-2 text-gray-700 placeholder-gray-500 bg-white border rounded-md dark:bg-gray-800 dark:border-gray-600 dark:placeholder-gray-400 focus:border-blue-500 dark:focus:border-blue-500 focus:outline-none focus:ring" placeholder="Adresse e-mail"
											 type="email"/>
							</tw-input-text>
						</validation-provider>
					</div>

					<div class="w-full mt-4">
						<validation-provider #default="{invalid, errors}" name="mot de passe" rules="required|confirmed:confirmation" slim>
							<tw-input-text :error="invalid" :error-message="errors[0]" class="my-2" label="Mot de passe" label-for="input_password" required>
								<input id="input_password" v-model="form.confirm_password" aria-label="Mot de passe"
											 class="block w-full px-4 py-2 mt-2 text-gray-700 placeholder-gray-500 bg-white border rounded-md dark:bg-gray-800 dark:border-gray-600 dark:placeholder-gray-400 focus:border-blue-500 dark:focus:border-blue-500 focus:outline-none focus:ring" placeholder="Mot de passe"
											 type="password"/>
							</tw-input-text>
						</validation-provider>
					</div>

					<div class="w-full mt-4">
						<validation-provider #default="{invalid, errors}" name="confirmation mot de passe" rules="required" slim vid="confirmation">
							<tw-input-text :error="invalid" :error-message="errors[0]" class="my-2" label="Confirmation du mot de passe" label-for="input_confirm_password" required>
								<input id="input_confirm_password" v-model="form.password" aria-label="Confirmation du mot de passe"
											 class="block w-full px-4 py-2 mt-2 text-gray-700 placeholder-gray-500 bg-white border rounded-md dark:bg-gray-800 dark:border-gray-600 dark:placeholder-gray-400 focus:border-blue-500 dark:focus:border-blue-500 focus:outline-none focus:ring"
											 placeholder="Confirmation du mot de passe" type="password"/>
							</tw-input-text>
						</validation-provider>
					</div>

					<div class="w-full mt-4">
						<validation-provider #default="{invalid, errors}" name="nom" rules="required" slim>
							<tw-input-text :error="invalid" :error-message="errors[0]" class="my-2" label="Nom" label-for="input_lastname" required>
								<input id="input_lastname" v-model="form.lastname" aria-label="Nom"
											 class="block w-full px-4 py-2 mt-2 text-gray-700 placeholder-gray-500 bg-white border rounded-md dark:bg-gray-800 dark:border-gray-600 dark:placeholder-gray-400 focus:border-blue-500 dark:focus:border-blue-500 focus:outline-none focus:ring" placeholder="Nom" type="email"/>
							</tw-input-text>
						</validation-provider>

						<div class="w-full mt-4">
							<validation-provider #default="{invalid, errors}" name="prénom" rules="required" slim>
								<tw-input-text :error="invalid" :error-message="errors[0]" class="my-2" label="Prénom" label-for="input_firstname" required>
									<input id="input_firstname" v-model="form.firstname" aria-label="Prénom"
												 class="block w-full px-4 py-2 mt-2 text-gray-700 placeholder-gray-500 bg-white border rounded-md dark:bg-gray-800 dark:border-gray-600 dark:placeholder-gray-400 focus:border-blue-500 dark:focus:border-blue-500 focus:outline-none focus:ring" placeholder="Prénom"
												 type="email"/>
								</tw-input-text>
							</validation-provider>
						</div>

						<div class="w-full mt-4">
							<validation-provider #default="{invalid, errors}" name="emploi" rules="required" slim>
								<tw-input-text :error="invalid" :error-message="errors[0]" class="my-2" label="Emploi" label-for="input_job" required>
									<input id="input_job" v-model="form.job" aria-label="Emploi"
												 class="block w-full px-4 py-2 mt-2 text-gray-700 placeholder-gray-500 bg-white border rounded-md dark:bg-gray-800 dark:border-gray-600 dark:placeholder-gray-400 focus:border-blue-500 dark:focus:border-blue-500 focus:outline-none focus:ring" placeholder="Emploi"
												 type="email"/>
								</tw-input-text>
							</validation-provider>
						</div>

						<div class="flex items-center justify-between mt-4">
							<button class="px-4 py-2 leading-5 text-white transition-colors duration-200 transform bg-gray-700 rounded hover:bg-gray-600 focus:outline-none" type="submit">Créer le compte</button>
						</div>
					</div>
				</validation-observer>
			</div>

			<div class="flex items-center justify-center py-4 text-center bg-gray-50 dark:bg-gray-700">
				<span class="text-sm text-gray-600 dark:text-gray-200">Déjà un compte ? </span>

				<nuxt-link class="mx-2 text-sm font-bold text-blue-500 dark:text-blue-400 hover:underline" to="/login">Connexion</nuxt-link>
			</div>
		</div>
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
