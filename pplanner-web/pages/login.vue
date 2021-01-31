<template>
	<div class="flex w-full justify-center items-center h-full">
		<validation-observer ref="validator" class="md:w-1/2 md:mx-0 w-full border border-primary p-6 mx-4 rounded-xl" tag="form" @submit.prevent="handleSubmitLoginForm">
			<div class="text-3xl">Connexion</div>
			<validation-provider #default="{invalid, errors}" name="adresse e-mail" rules="required|email" slim>
				<tw-input-text :error="invalid" :error-message="errors[0]" class="my-2" label="Adresse e-mail" label-for="input_email" required>
					<input id="input_email" v-model="form.username" class="form-control" type="email">
				</tw-input-text>
			</validation-provider>
			<validation-provider #default="{invalid, errors}" name="mot de passe" rules="required" slim>
				<tw-input-text :error="invalid" :error-message="errors[0]" class="my-2" label="Mot de passe" label-for="input_password" required>
					<input id="input_password" v-model="form.password" class="form-control" type="password">
				</tw-input-text>
			</validation-provider>
			<div class="flex justify-end">
				<button class="p-btn p-btn--secondary" type="submit">Se connecter</button>
			</div>
		</validation-observer>
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
				console.log(err);
			}
		}
	}
}
</script>

<style>

</style>
