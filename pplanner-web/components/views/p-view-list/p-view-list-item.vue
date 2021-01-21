<template>
	<div>

	</div>
</template>

<script lang="ts">
import {Component, Vue} from 'nuxt-property-decorator'
import {VModel} from "vue-property-decorator";

@Component({})
export default class PViewListItem extends Vue {
	@VModel() item: any
}
</script>

<style>

</style>
