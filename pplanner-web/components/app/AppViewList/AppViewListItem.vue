<template>
	<div>
		<div v-for="(item, key, index) in tasks">
			{{ getStatusName(key) }}
		</div>
	</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'nuxt-property-decorator'

@Component({})
export default class AppViewListItem extends Vue {
	@Prop({ required: true }) tasks: any;
	@Prop({ required: true }) status;

	getStatusName (statusId) {
		return this.status.find(st => st.id === statusId)
	}
}
</script>

<style>

</style>
