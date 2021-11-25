import { Component, OnInit } from '@angular/core'
import { FormControl, FormGroup } from '@angular/forms'
import { AuthService } from '../../services/auth.service'

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: [ './login.component.scss' ],
})
export class LoginComponent implements OnInit {

	form: FormGroup = new FormGroup({
		username: new FormControl('username'),
		password: new FormControl('password'),
	})

	constructor (private authService: AuthService) {
	}

	ngOnInit (): void {
	}

	login () {
		this.authService.login('kestounet@gmail.com', '123')
	}

}
