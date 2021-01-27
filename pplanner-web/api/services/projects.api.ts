import { Repository } from '~/api/Repository'


export class ProjectsApi extends Repository {
	findAll () {
		return this.axios.$get('/projects')
	}

	findById (projectId) {
		return this.axios.$get(`/projects/${ projectId }`)
	}

	findAllWorkspaceByProjectId (projectId) {
		return this.axios.$get(`/projects/${ projectId }/workspaces`)
	}
}

