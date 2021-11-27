import {Component, OnDestroy, OnInit} from '@angular/core'
import {Subscription} from 'rxjs'
import {AlertService} from './alert.service'
import {Alert, AlertType} from '../ui.models'

@Component({
	selector: 'tw-alert',
	templateUrl: './alert.component.html',
	styleUrls: ['./alert.component.scss']
})
export class AlertComponent implements OnInit, OnDestroy {

	alerts: Alert[] = []
	private _subscription: Subscription

	constructor(private alertService: AlertService) {
	}

	ngOnInit() {
		this._subscription = this.alertService.getObservable().subscribe(notification => this._addNotification(notification))
	}

	ngOnDestroy() {
		this._subscription.unsubscribe()
	}

	close(alert: Alert) {
		this.alerts = this.alerts.filter(notif => notif.id !== alert.id)
	}

	className(alert: Alert): string {
		switch (alert.type) {
			case AlertType.success:
				return 'success'
			case AlertType.warn:
				return 'warning'
			case AlertType.danger:
				return 'error'
			default:
				return 'info'
		}
	}

	iconClass(alert: Alert): string {
		switch (alert.type) {
			case AlertType.success:
				return 'fas fa-check text-white'
			case AlertType.warn:
				return 'fas fa-exclamation text-white'
			case AlertType.danger:
				return 'fas fa-bolt text-white'
			default:
				return 'fas fa-info text-white'
		}
	}

	private _addNotification(alert: Alert) {
		this.alerts.push(alert)

		if (alert.timeout !== 0) {
			setTimeout(() => this.close(alert), alert.timeout)
		}
	}
}
