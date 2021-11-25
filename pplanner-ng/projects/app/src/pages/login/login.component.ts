import { Component, OnInit } from '@angular/core'
import { FormBuilder, FormGroup, Validators } from '@angular/forms'
import { AuthService } from '../../services/auth.service'

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: [ './login.component.scss' ],
})
export class LoginComponent implements OnInit {

	form: FormGroup

	constructor (private authService: AuthService, fb: FormBuilder) {
		this.form = fb.group({
			username: [ '', Validators.required ],
			password: [ '', Validators.required ],
		})
	}

	ngOnInit (): void {
		this.form.setValue({ username: 'kestounet@gmail.com', password: '123' })
	}

	login () {
		if (this.form.valid) {
			this.authService.login(this.form.value.username, this.form.value.password)
		}
	}

}
