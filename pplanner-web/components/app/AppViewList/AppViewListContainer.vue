<template>
	<div>
		<div v-for="(tasks, statusId) in tasksByStatus">
			<app-view-list-item :status-id="statusId" :status-list="statusList" :tasks="tasks"></app-view-list-item>
		</div>
	</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'nuxt-property-decorator'
import AppViewListItem from "~/components/app/AppViewList/AppViewListItem.vue";

@Component({
	components: {
		AppViewListItem
	}
})
export default class AppViewListContainer extends Vue {
	@Prop({ required: true }) tasksByStatus: any;
	@Prop({ required: true }) menuItem: any;

	public statusList: any [] = []

	async created () {
		this.statusList = await this.$axios.$get(`/task_status/items/${ this.menuItem.id }`)
	}
}
</script>

<style>

</style>
