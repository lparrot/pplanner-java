import { Component, OnInit } from '@angular/core'
import { AuthService } from '../../services/auth.service'

@Component({
	selector: 'app-layout-default',
	templateUrl: './layout-default.component.html',
	styleUrls: [ './layout-default.component.scss' ],
})
export class LayoutDefaultComponent implements OnInit {

	showMenu = false

	constructor (public authService: AuthService) {
	}

	ngOnInit (): void {
	}

	toggleNavbar () {
		this.showMenu = !this.showMenu
	}

}
