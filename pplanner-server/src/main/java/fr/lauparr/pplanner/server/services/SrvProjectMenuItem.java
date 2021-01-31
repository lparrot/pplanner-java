package fr.lauparr.pplanner.server.services;

import fr.lauparr.pplanner.server.dao.DaoProjectMenuItem;
import fr.lauparr.pplanner.server.entities.ProjectMenuItem;
import fr.lauparr.pplanner.server.entities.User;
import fr.lauparr.pplanner.server.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SrvProjectMenuItem {

	@Autowired
	private DaoProjectMenuItem daoProjectMenuItem;

	public ProjectMenuItem findById(final String itemId, final User user) {
		return this.daoProjectMenuItem.findById(itemId, user).orElseThrow(NotFoundException::new);
	}

	public ProjectMenuItem findByIdAndProjectId(final String itemId, final String projectId, final User user) {
		return this.daoProjectMenuItem.findByIdAndProjectId(itemId, projectId, user).orElseThrow(NotFoundException::new);
	}

	public List<ProjectMenuItem> findAllWorkspaceByProjectId(final String projectId, final User user) {
		return this.daoProjectMenuItem.findAllWorkspaceByProjectId(projectId, user);
	}

	public ProjectMenuItem findFirstWorkspaceByProjectId(final String projectId, final User user) {
		final List<ProjectMenuItem> allWorkspaceByProjectId = this.findAllWorkspaceByProjectId(projectId, user);
		if (allWorkspaceByProjectId.size() > 0) {
			return allWorkspaceByProjectId.get(0);
		}
		throw new NotFoundException();
	}
}
