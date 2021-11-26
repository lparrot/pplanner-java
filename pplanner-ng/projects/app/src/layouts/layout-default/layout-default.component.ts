import {Component, OnInit} from '@angular/core'
import {AuthService} from '../../services/auth.service'
import {Router} from '@angular/router'

@Component({
	selector: 'app-layout-default',
	templateUrl: './layout-default.component.html',
	styleUrls: ['./layout-default.component.scss'],
})
export class LayoutDefaultComponent implements OnInit {

	showMenu = false

	constructor(public authService: AuthService, private router: Router) {
	}

	ngOnInit(): void {
	}

	toggleNavbar() {
		this.showMenu = !this.showMenu
	}

	async logout() {
		this.authService.logout()
			.subscribe(() => {
				this.router.navigateByUrl('/login')
			})
	}
}
