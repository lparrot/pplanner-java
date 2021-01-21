import {Repository} from '~/api/Repository'


export class ProjectsApi extends Repository {
	findAll() {
		return this.axios.$get('/projects')
	}

	findAllWorkspaceByProjectId(projectId) {
		return this.axios.$get(`/projects/${projectId}/workspaces`)
	}
}

