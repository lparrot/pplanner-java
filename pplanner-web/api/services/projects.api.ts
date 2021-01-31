import { Repository } from '~/api/Repository'
import { ApiLog } from '~/decorators/ApiLog'

@ApiLog
export class ProjectsApi extends Repository {
	findAllByCreatorAndMember () {
		return this.axios.$get('/projects')
	}

	findById (projectId) {
		if (projectId == null) {
			return null
		}
		return this.axios.$get(`/projects/${ projectId }`)
	}

	findAllWorkspaceByProjectId (projectId) {
		if (projectId == null) {
			return []
		}
		return this.axios.$get(`/projects/${ projectId }/workspaces`)
	}

	createProject (body: BodyCreateProject) {
		if (body != null) {
			return this.axios.$post(`/projects`, body)
		}
	}
}

type BodyCreateProject = {
	name: string
}
