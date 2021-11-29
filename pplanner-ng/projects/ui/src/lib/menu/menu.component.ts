import {Component, HostListener, Input} from '@angular/core'
import {MenuOverlayComponent} from '../menu-overlay/menu-overlay.component'

@Component({
	selector: 'tw-menu',
	template: `
		<div class="mt-2 w-56 rounded-md shadow-lg bg-white dark:bg-gray-800 ring-1 ring-black ring-opacity-5">
			<div aria-labelledby="options-menu" aria-orientation="vertical" class="py-1 " role="menu">
				<ng-content></ng-content>
			</div>
		</div>
	`
})
export class MenuComponent {

	@Input() overlayRef: MenuOverlayComponent

	constructor() {
	}

	closeOverlay() {
		if (this.overlayRef != null) {
			this.overlayRef.close()
		}
	}

}

@Component({
	selector: 'tw-menu-item',
	template: `
		<div class="block px-4 py-2 text-md hover:bg-primary-100 hover:text-primary-900" [class]="{'cursor-pointer': link}" role="menuitem">
			<ng-content></ng-content>
		</div>
	`
})
export class MenuItemComponent {

	@Input() link: boolean = true

	constructor(private menu: MenuComponent) {
	}

	@HostListener('click', ['$event'])
	onClick() {
		this.menu.closeOverlay()
	}

}
