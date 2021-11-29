import {Component, Input, OnDestroy, OnInit} from '@angular/core'
import {Subscription} from 'rxjs'
import {AlertService} from './alert.service'
import {Alert, AlertPosition, AlertType} from '../ui.models'

@Component({
	selector: 'tw-alert',
	templateUrl: './alert.component.html',
	styleUrls: ['./alert.component.scss']
})
export class AlertComponent implements OnInit, OnDestroy {

	@Input() position: AlertPosition = 'bottomLeft'

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

	get alertsPosition() {
		switch (this.position) {
			case 'bottom':
				return 'bottom-0 left-1/2 transform -translate-x-1/2'
			case 'top':
				return 'top-0 left-1/2 transform -translate-x-1/2'
			case 'bottomLeft':
				return 'bottom-0 left-0'
			case 'bottomRight':
				return 'bottom-0 right-0'
			case 'topLeft':
				return 'top-0 left-0'
			case 'topRight':
				return 'top-0 right-0'
			default:
				return 'bottom-0 right-0'
		}
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
