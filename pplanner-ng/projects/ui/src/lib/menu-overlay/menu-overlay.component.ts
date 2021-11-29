import {Component, Input, OnInit} from '@angular/core'

@Component({
	selector: 'tw-menu-overlay',
	template: `
		<div #trigger="cdkOverlayOrigin" cdkOverlayOrigin>
			<ng-content select="[activator]"></ng-content>
		</div>

		<ng-template cdkConnectedOverlay (overlayOutsideClick)="isOpen = false" [cdkConnectedOverlayDisableClose]="false" [cdkConnectedOverlayHasBackdrop]="true" [cdkConnectedOverlayOpen]="isOpen" [cdkConnectedOverlayOrigin]="trigger" cdkConnectedOverlayBackdropClass="cdk-overlay-transparent-backdrop">
			<ng-content></ng-content>
		</ng-template>
	`
})
export class MenuOverlayComponent implements OnInit {

	@Input() persistent: boolean

	isOpen: boolean

	constructor() {
	}

	ngOnInit(): void {
	}

	show() {
		this.isOpen = true
	}

	close() {
		this.isOpen = false
	}

	toggle() {
		this.isOpen = !this.isOpen
	}
}
