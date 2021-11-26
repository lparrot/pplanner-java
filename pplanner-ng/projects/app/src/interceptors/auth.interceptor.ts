import {Injectable} from '@angular/core'
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http'
import {Observable} from 'rxjs'
import {AuthService} from '../services/auth.service'

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

	constructor(private authService: AuthService) {
	}

	intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
		const token = this.authService.getAuthToken()

		if (token) {
			const authReq = request.clone({
				headers: request.headers.set('Authorization', token),
			})
			return next.handle(authReq)
		} else {
			return next.handle(request)
		}
	}
}
