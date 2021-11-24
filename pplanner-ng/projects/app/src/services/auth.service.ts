import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'

@Injectable({
	providedIn: 'root',
})
export class AuthService {

	public token: string = null
	public user: any = null

	constructor (private http: HttpClient) {
	}

	public login (username: string, password: string) {
		this.http.post<any>('/api/auth/login', { username, password })
			.subscribe(data => {
				this.token = data.token
				this.user = data.claims
			})
	}

	public isLoggedIn () {
		return this.user != null
	}
}
