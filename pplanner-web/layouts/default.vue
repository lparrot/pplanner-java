<template>
	<div class="h-full flex">
		<app-vertical-navbar :items="items" class="flex flex-col">
			<template #bottom-icons>
				<i class="fas fa-sign-out-alt rounded-full p-2 hover:bg-primary-400 cursor-pointer" @click="logout"></i>
			</template>

			<template #projects>
				<div class="flex flex-col gap-2">
					<div v-for="project in projects" class="p-2 cursor-pointer rounded bg-secondary-400 text-white" @click="handleSelectProject(project)">{{ project.name.substring(0, 1) }}</div>
					<div class="p-2 cursor-pointer rounded text-base" @click="handleShowModalCreateProject">
						<i class="fas fa-plus"></i>
					</div>
				</div>
			</template>
		</app-vertical-navbar>

		<nuxt ref="nuxt_navigation"></nuxt>

		<template v-if="project != null">
			<validation-observer ref="validator" tag="form" @submit.prevent="handleSubmitCreateProject">
				<tw-modal :visible.sync="modals.createProject" title="Création d'un nouveau projet">

					<validation-provider #default="{invalid, errors}" name="nom" rules="required">
						<tw-input-text v-model.trim="project.name" :error="invalid" :error-message="errors[0]" label="Nom du projet"></tw-input-text>
					</validation-provider>

					<template #actions>
						<button class="p-btn p-btn--primary" @click="modals.createProject = false">Annuler</button>
						<button class="p-btn p-btn--secondary" type="submit">Créer</button>
					</template>
				</tw-modal>
			</validation-observer>
		</template>
	</div>
</template>

<script lang="ts">
import { Action, Component, Ref, Vue } from 'nuxt-property-decorator'
import AppVerticalNavbar from "~/components/app/AppVerticalNavbar.vue";
import TwModal from "~/components/shared/TwModal.vue";
import TwInputText from "~/components/shared/TwInputText.vue";

@Component({
	components: {
		TwInputText,
		TwModal,
		AppVerticalNavbar,
	},
})
export default class LayoutDefault extends Vue {

	@Ref('validator') validator

	@Action('logout') logout
	@Action('selectProject') selectProject

	public items: Partial<Models.PVerticalNavbarItem>[] = []
	public projects: any[] = []
	public project: any = null
	public modals = {
		createProject: false
	}

	async fetch () {
		this.items = [
			{ name: 'Tâches', to: '/tasks', icon: 'fas fa-tasks' },
			{ name: 'Notifications', to: '/notification', icon: 'fas fa-bell' },
			{ name: 'Tableau de bord', to: '/dashboard', icon: 'fas fa-th-large' },
			{ type: 'divider' },
			{ type: 'slot', name: 'projects' }
		]

		await this.fetchProjects()
	}

	async fetchProjects () {
		this.projects = await this.$api.projects.findAllByCreatorAndMember()
	}

	async handleSelectProject (project) {
		await this.selectProject(project.id)
		await this.$router.push('/tasks')
		this.$fetch()
	}

	async handleShowModalCreateProject () {
		this.project = {}
		this.modals.createProject = true
	}

	async handleSubmitCreateProject () {
		const valid = await this.validator.validate()
		if (valid) {
			await this.$api.projects.createProject(this.project)
			this.modals.createProject = false
			await this.fetchProjects()
		}
	}
}
</script>

<style>

</style>
