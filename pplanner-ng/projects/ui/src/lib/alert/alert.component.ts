import {Component, Input, OnDestroy, OnInit} from '@angular/core'
import {Subscription} from 'rxjs'
import {AlertService} from './alert.service'
import {Alert, AlertPosition, AlertType} from '../ui.models'

@Component({
	selector: 'tw-alert',
	template: `
		<div [ngClass]="alertsPosition" class="alerts">
			<div *ngFor="let alert of alerts" [ngClass]="className(alert)" class="alert animate-bounce-in-right">
				<ng-container *ngTemplateOutlet="alertTemplate; context:{alert:alert}"></ng-container>
			</div>
		</div>

		<ng-template #alertTemplate let-alert="alert">
			<div class="flex overflow-hidden bg-white rounded-lg shadow-md">
				<div class="alert-{{alert.type}}--icon">
					<i [ngClass]="iconClass(alert)" class="w-12 text-center"></i>
				</div>

				<div class="px-4 py-2 -mx-3">
					<div class="mx-3">
						<span class="alert-{{alert.type}}--title">{{alert.title}}</span>
						<p class="text-sm text-gray-600 dark:text-gray-200">{{alert.message}}</p>
					</div>
				</div>
			</div>
		</ng-template>
	`,
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
