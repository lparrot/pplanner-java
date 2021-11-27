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
		this.alert.success('Hello there', 'How are you today ?', 5000)
		this.alert.info('Hello there', 'How are you today ?', 5000)
		this.alert.warn('Hello there', 'How are you today ?', 5000)
		this.alert.danger('Hello there', 'How are you today ?', 5000)
	}
}
