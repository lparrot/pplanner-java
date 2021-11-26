import {Injectable} from '@angular/core'
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router'
import {AuthService} from '../services/auth.service'

@Injectable({
	providedIn: 'root',
})
export class AuthGuard implements CanActivate {
	constructor(private router: Router, private authService: AuthService) {
	}

	async canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
		if (this.authService.isLoggedIn()) {
			return true
		} else {
			await this.router.navigateByUrl('/login')
			return false
		}
	}
}