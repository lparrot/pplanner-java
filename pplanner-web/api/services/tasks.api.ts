import mock from '~/mocks/api.json'
import {Repository} from '~/api/Repository'
import ProjectMenuItem = Models.ProjectMenuItem

export class TasksApi extends Repository {
	findById(taskId) {
		return mock.projectItems.find(item => item.id === taskId)
	}

	findFirstWorkspaceByProjectId(projectId) {
		return mock.projectItems.find(item => item.type === 'WORKSPACE' && item.project === projectId)
	}

	findAllTaskByProjectId(projectId: number): ProjectMenuItem[] {
		const allWorkspaceByProjectId = mock.projectItems.filter(item => item.type === 'WORKSPACE' && item.project === projectId)

		return allWorkspaceByProjectId.map(item => {
			item = this._findChildren(item)
			item['children'].map(child => this._findChildren(child))
			return item
		})
	}

	_findChildren(item) {
		const children = mock.projectItems.filter(child => child.parent === item.id)
		if (children != null && children.length > 0) {
			item['children'] = children
		}
		return item
	}
}
