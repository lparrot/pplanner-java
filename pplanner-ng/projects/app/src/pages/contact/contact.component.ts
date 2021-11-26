import {Component, OnInit} from '@angular/core'
import {FormBuilder, FormGroup, Validators} from '@angular/forms'

@Component({
	selector: 'app-contact',
	templateUrl: './contact.component.html',
	styleUrls: ['./contact.component.scss'],
})
export class ContactComponent implements OnInit {

	form: FormGroup

	constructor(fb: FormBuilder) {
		this.form = fb.group({
			name: ['', Validators.required],
			email: ['', Validators.compose([Validators.required, Validators.email])],
			message: ['', Validators.required],
		})
	}

	ngOnInit(): void {
	}

	contact() {
		if (this.form.valid) {
			console.log(this.form.value)
		}
	}
}
