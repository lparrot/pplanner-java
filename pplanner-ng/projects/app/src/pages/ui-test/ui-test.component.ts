import {Component, OnInit} from '@angular/core'
import {AlertService} from '../../../../ui/src/lib/alert/alert.service'

@Component({
	selector: 'app-ui-test',
	templateUrl: './ui-test.component.html',
	styleUrls: ['./ui-test.component.scss'],
})
export class UiTestComponent implements OnInit {

	constructor(public alert: AlertService) {
	}

	ngOnInit(): void {
	}

	generateAlert() {
		const variants = ['success', 'info', 'warn', 'danger']
		const variant = variants[Math.floor(Math.random() * variants.length)]

		// @ts-ignore
		this.alert[variant]('Hello there', 'How are you today ?', 5000)
	}
}
