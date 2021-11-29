import {Component, OnInit} from '@angular/core'
import {FormBuilder, FormGroup, Validators} from '@angular/forms'
import {AuthService} from '../../services/auth.service'
import {Router} from '@angular/router'
import {AlertService} from '../../../../ui/src/lib/alert/alert.service'

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {

	form: FormGroup

	constructor(private $router: Router, private $auth: AuthService, private $alert: AlertService, fb: FormBuilder) {
		this.form = fb.group({
			username: ['', Validators.compose([Validators.required, Validators.email])],
			password: ['', Validators.required],
		})
	}

	ngOnInit(): void {
		this.form.setValue({username: 'kestounet@gmail.com', password: '123'})
	}

	login() {
		if (this.form.valid) {
			this.$auth.login(this.form.value.username, this.form.value.password)
				.subscribe(({claims}) => {
					this.$router.navigateByUrl('/ui-test')
					this.$alert.info('Connexion', 'Vous êtes connecté en tant que ' + claims.sub)
				})
		}
	}

}
