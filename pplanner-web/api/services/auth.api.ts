import { Repository } from '~/api/Repository'
import { ApiLog } from '~/decorators/ApiLog'

@ApiLog
export class AuthApi extends Repository {
	getUserData () {
		return this.axios.$get('/auth/user_data')
	}

	createAccount (body: Partial<BodyCreateAccount>) {
		return this.axios.$post('/auth', body)
	}
}

type BodyCreateAccount = {
	email: string
	password: string
	firstname: string
	lastname: string
	job: string
}
