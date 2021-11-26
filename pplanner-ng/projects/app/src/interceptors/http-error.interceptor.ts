import {Injectable} from '@angular/core'
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http'
import {catchError, Observable, throwError} from 'rxjs'

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {

	constructor() {
	}

	intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
		return next.handle(request)
			.pipe(
				catchError((error: HttpErrorResponse) => {
					let errorMessage = error.error.message
					window.alert(errorMessage)
					return throwError(() => error.error)
				}),
			)

	}
}
