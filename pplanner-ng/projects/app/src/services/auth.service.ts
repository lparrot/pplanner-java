import {Injectable} from '@angular/core'
import {HttpClient} from '@angular/common/http'
import {lastValueFrom, tap} from 'rxjs'
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

	async logout() {
		this.token = null
		this.user = null
		this.clearAuthToken()
		await this.router.navigateByUrl('/login')
	}

	async getUser() {
		const token = this.getAuthToken()

		if (token != null) {
			await lastValueFrom(this.http.get('/api/auth/user')
				.pipe(
					tap(data => {
						this.token = token
						this.user = data
					})
				)
			)
		}
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
