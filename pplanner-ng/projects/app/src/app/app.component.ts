import { Component } from '@angular/core'
import { AuthService } from '../services/auth.service'
import { FormControl, FormGroup } from '@angular/forms'

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: [ './app.component.scss' ],
})
export class AppComponent {

	showMenu = false
	form: FormGroup = new FormGroup({
		username: new FormControl('username'),
		password: new FormControl('password'),
	})

	constructor (public authService: AuthService) {
	}

	toggleNavbar () {
		this.showMenu = !this.showMenu
	}

	login () {
		this.authService.login('kestounet@gmail.com', '123')
	}
}
