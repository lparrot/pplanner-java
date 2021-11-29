import {Component, OnInit, ViewEncapsulation} from '@angular/core'
import {AuthService} from '../../services/auth.service'
import {Router} from '@angular/router'
import {AlertService} from '../../../../ui/src/lib/alert/alert.service'

@Component({
	selector: 'app-layout-default',
	templateUrl: './layout-default.component.html',
	styleUrls: ['./layout-default.component.scss'],
	encapsulation: ViewEncapsulation.None
})
export class LayoutDefaultComponent implements OnInit {

	showMenu = false

	constructor(public $auth: AuthService, private $router: Router, private $alert: AlertService) {
	}

	ngOnInit(): void {
	}

	toggleNavbar() {
		this.showMenu = !this.showMenu
	}

	async logout() {
		this.$auth.logout()
			.subscribe(() => {
				this.$router.navigateByUrl('/login')
				this.$alert.success('Deconnexion', 'Vous êtes à présent déconnecté')
			})
	}
}
