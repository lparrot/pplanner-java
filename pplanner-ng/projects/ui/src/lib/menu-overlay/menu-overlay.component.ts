import { Component, Input, OnInit } from '@angular/core'

@Component({
	selector: 'tw-menu-overlay',
	templateUrl: './menu-overlay.component.html',
	styleUrls: [ './menu-overlay.component.scss' ],
})
export class MenuOverlayComponent implements OnInit {

	@Input() persistent: boolean

	show: boolean

	constructor () {
	}

	ngOnInit (): void {
	}

	toggle (backdrop?: boolean) {
		console.log(backdrop)
		if (!backdrop || (backdrop && !this.persistent)) {
			this.show = !this.show
		}
	}
}
