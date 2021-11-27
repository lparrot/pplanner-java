import {Injectable} from '@angular/core'
import {Observable, Subject} from 'rxjs'
import {Alert, AlertType} from '../ui.models'

@Injectable({
	providedIn: 'root'
})
export class AlertService {
	private _subject = new Subject<Alert>()
	private _idx = 0

	constructor() {
	}

	getObservable(): Observable<Alert> {
		return this._subject.asObservable()
	}

	info(title: string, message: string, timeout = 3000) {
		this._subject.next(new Alert(this._idx++, AlertType.info, title, message, timeout))
	}

	success(title: string, message: string, timeout = 3000) {
		this._subject.next(new Alert(this._idx++, AlertType.success, title, message, timeout))
	}

	warn(title: string, message: string, timeout = 3000) {
		this._subject.next(new Alert(this._idx++, AlertType.warn, title, message, timeout))
	}

	danger(title: string, message: string, timeout = 0) {
		this._subject.next(new Alert(this._idx++, AlertType.danger, title, message, timeout))
	}
}
