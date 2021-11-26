import {Injectable} from '@angular/core'
import {AuthService} from './auth.service'
import {firstValueFrom} from 'rxjs'

@Injectable({
	providedIn: 'root'
})
export class StartupService {

	constructor(private authService: AuthService) {
	}

	async initialize() {
		await firstValueFrom(this.authService.getUser())
	}
}
