<template>
	<div class="p-container items-center">
		<!-- CTA -->
		<div class="bg-white dark:bg-gray-800 ">
			<div class="text-center w-full mx-auto py-12 px-4 sm:px-6 lg:py-16 lg:px-8 z-20">
				<h2 class="text-3xl font-extrabold text-black dark:text-white sm:text-4xl">
            <span class="block">
                Vous avez un nouveau projet ou un projet existant ?
            </span>
					<span class="block text-indigo-500">
                PPlanner est fait pour vous !
            </span>
				</h2>
				<div class="lg:mt-0 lg:flex-shrink-0">
					<div class="mt-12 inline-flex rounded-md shadow">
						<router-link class="py-4 px-6 bg-indigo-600 hover:bg-indigo-700 focus:ring-indigo-500 focus:ring-offset-indigo-200 text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2 rounded-lg" tag="button" to="/tasks" type="button">
							C'est parti
						</router-link>
					</div>
				</div>
			</div>
		</div>

		<hr class="flex flex-col w-1/2 my-24">

		<!-- FEATURE -->

		<section>
			<div class="container max-w-7xl mx-auto p-4 sm:p-6 lg:p-8 bg-white dark:bg-gray-800">
				<div class="flex flex-wrap -mx-8">
					<div class="w-full lg:w-1/2 px-8">
						<div class="mb-12 lg:mb-0 pb-12 lg:pb-0 border-b lg:border-b-0">
							<h2 class="mb-4 text-3xl lg:text-4xl font-bold font-heading dark:text-white">
								PPlanner, l'outil indispensable pour les chefs de projet</h2>
							<p class="mb-8 leading-loose text-gray-500 dark:text-gray-300">
								Vous en avez assez de passer du temps sur plusieurs outils différents, a créer 50 tableaux Excel ou devoir développer des outils par vous même ?
								PPlanner vous permettra de palier à ces problèmes et vous fournira tout l'attirail nécessaire pour mener à bien votre projet.</p>
							<div class="w-full md:w-1/3">
								<button class="py-2 px-4  bg-indigo-600 hover:bg-indigo-700 focus:ring-indigo-500 focus:ring-offset-indigo-200 text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2  rounded-lg " type="button">
									Voir plus...
								</button>
							</div>
						</div>
					</div>
					<div class="w-full lg:w-1/2 px-8">
						<ul class="space-y-12">
							<li class="flex -mx-4">
								<div class="px-4">
									<span class="flex w-16 h-16 mx-auto items-center justify-center text-2xl font-bold font-heading rounded-full bg-blue-50 text-blue-600">
										1
									</span>
								</div>
								<div class="px-4">
									<h3 class="my-4 text-xl font-semibold dark:text-white">
										Organisation du travail dynamique et simplifiée </h3>
									<p class="text-gray-500 dark:text-gray-300 leading-loose">
										Grâce à son système de vue, PPlanner vous permettra d'utiliser différents outils, de les organiser comme bon vous semble et de les partager avec vos équipes. </p>
								</div>
							</li>
							<li class="flex -mx-4">
								<div class="px-4">
                  <span class="flex w-16 h-16 mx-auto items-center justify-center text-2xl font-bold font-heading rounded-full bg-blue-50 text-blue-600">
										2
									</span>
								</div>
								<div class="px-4">
									<h3 class="my-4 text-xl font-semibold dark:text-white">
										Partage de fichiers liés aux projets </h3>
									<p class="text-gray-500 dark:text-gray-300 leading-loose">
										Il est possible de partager des fichiers liés au projet, disponible pour vos équipes ou des personnes extérieures en créant des liens temporaires. </p>
								</div>
							</li>
							<li class="flex -mx-4">
								<div class="px-4">
									<span class="flex w-16 h-16 mx-auto items-center justify-center text-2xl font-bold font-heading rounded-full bg-blue-50 text-blue-600">
										3
									</span>
								</div>
								<div class="px-4">
									<h3 class="my-4 text-xl font-semibold dark:text-white">
										Restitutions graphiques et génération de rapports </h3>
									<p class="text-gray-500 dark:text-gray-300 leading-loose">
										Votre chef vous demande de lui rendre compte de l'avancée du projet ou du travail réalisé par votre équipe dans le mois ?
										Il vous sera possible de ressortir tous les indicateurs nécessaires et lui fournir en temps réel. </p>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</section>

		<hr class="flex flex-col w-1/2 my-24">

		<!-- TEAM -->

		<div class="flex flex-col items-center justify-center p-8 bg-white dark:bg-gray-800 rounded-lg">
			<p class="text-center text-3xl font-bold text-gray-800 dark:text-white">
				Equipe professionnelle </p>
			<p class="text-center mb-12 text-xl font-normal text-gray-500 dark:text-gray-200">
				Rencontrez la meilleure équipe au monde ! </p>
			<div class="flex items-center flex-col md:flex-row justify evenly">
				<div class="p-4">
					<div class="text-center mb-4 opacity-90">
						<a class="block relative" href="#">
							<img alt="profil" class="mx-auto rounded-full h-40 w-40 " src="https://i.pravatar.cc/300?img=3"/>
						</a>
					</div>
					<div class="text-center">
						<p class="text-2xl text-gray-800 dark:text-white">
							Patrick Sebastien </p>
						<p class="text-xl text-gray-500 dark:text-gray-200 font-light">
							Developpeur </p>
						<p class="text-md text-gray-500 dark:text-gray-400 max-w-xs py-4 font-light">
							Patrick Sébastien, born November 14, 1953 in Brive-la-Gaillarde, is an imitator. </p>
					</div>
				</div>
				<div class="p-4">
					<div class="text-center mb-4 opacity-90">
						<a class="block relative" href="#">
							<img alt="profil" class="mx-auto rounded-full h-40 w-40 " src="https://i.pravatar.cc/300?img=6"/>
						</a>
					</div>
					<div class="text-center">
						<p class="text-2xl text-gray-800 dark:text-white">
							Jean Castux </p>
						<p class="text-xl text-gray-500 dark:text-gray-200 font-light">
							Scrum Master </p>
						<p class="text-md text-gray-500 dark:text-gray-400 max-w-xs py-4 font-light">
							Jean Castux is an imitator, humorist, actor, born November 14, 1953 in Pontivy. </p>
					</div>
				</div>
				<div class="p-4">
					<div class="text-center mb-4 opacity-90">
						<a class="block relative" href="#">
							<img alt="profil" class="mx-auto rounded-full h-40 w-40 " src="https://i.pravatar.cc/300?img=7"/>
						</a>
					</div>
					<div class="text-center">
						<p class="text-2xl text-gray-800 dark:text-white">
							Thierry Halliday </p>
						<p class="text-xl text-gray-500 dark:text-gray-200 font-light">
							Product Owner </p>
						<p class="text-md text-gray-500 dark:text-gray-400 max-w-xs py-4 font-light">
							Thierry Halliday, born November 4, 1993 in Saint hilaire de riez, is css specialist. </p>
					</div>
				</div>
			</div>
		</div>


	</div>
</template>

<script lang="ts">
import { Component, Vue } from 'nuxt-property-decorator'

@Component({
	layout: 'landing',
	auth: false,
})
export default class PageIndex extends Vue {
}
</script>

<style>

</style>
