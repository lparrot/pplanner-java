import {Component} from '@angular/core'
import {Router} from '@angular/router'
import {AuthService} from '../../services/auth.service'
import {FormBuilder, FormGroup, Validators} from '@angular/forms'

@Component({
	selector: 'app-register',
	templateUrl: './register.component.html',
	styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {

	form: FormGroup

	constructor(private router: Router, private authService: AuthService, fb: FormBuilder) {
		this.form = fb.group({
			email: ['', Validators.compose([Validators.required, Validators.email])],
			password: ['', Validators.required],
			firstname: [''],
			lastname: [''],
			job: [''],
		})
	}

	async register() {
		if (this.form.valid) {
			this.authService.register(this.form.value)
				.subscribe(() => this.router.navigateByUrl('/ui-test'))
		}
	}

}
