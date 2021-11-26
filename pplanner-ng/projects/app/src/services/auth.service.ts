import {Injectable} from '@angular/core'
import {HttpClient} from '@angular/common/http'
import {Observable, tap} from 'rxjs'
import {Router} from '@angular/router'

@Injectable({
	providedIn: 'root',
})
export class AuthService {

	public token: string = null
	public user: any = null

	constructor(private router: Router, private http: HttpClient) {
	}

	login(username: string, password: string) {
		return this.http.post<any>('/api/auth/login', {username, password})
			.pipe(
				tap(data => {
					this.token = data.token
					this.user = data.claims
					this.setAuthToken(data.token)
				}),
			)
	}

	logout() {
		return new Observable(observer => {
			this.token = null
			this.user = null
			this.clearAuthToken()
			observer.next()
		})
	}

	register(params: any) {
		return this.http.post('/api/auth', params, {responseType: 'text'})
			.pipe(
				tap((token: string) => this.setAuthToken(token)),
				tap(this.getUser())
			)
	}

	getUser() {
		return new Observable(subscriber => {
			const token = this.getAuthToken()

			if (token != null) {
				this.http.get('/api/auth/user')
					.subscribe(data => {
						this.token = token
						this.user = data
						subscriber.next()
					})
			} else {
				subscriber.next()
			}
		})
	}

	setAuthToken(token: string) {
		localStorage.setItem('token', token)
	}

	getAuthToken(): string {
		return localStorage.getItem('token')
	}

	clearAuthToken() {
		localStorage.removeItem('token')
	}

	public isLoggedIn() {
		return this.user != null
	}
}
