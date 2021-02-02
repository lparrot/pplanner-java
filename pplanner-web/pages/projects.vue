<template>
	<div>
		<template v-if=""></template>
	</div>
</template>

<script lang="ts">
import { Component, Vue } from 'nuxt-property-decorator'

@Component({})
export default class PageProjects extends Vue {

	async fetch () {
		const projects = await this.$api.projects.findAllByCreatorAndMember();
		console.log(projects);
	}

}
</script>

<style>

</style>
