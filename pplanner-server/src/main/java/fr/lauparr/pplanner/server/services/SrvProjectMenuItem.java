package fr.lauparr.pplanner.server.services;

import fr.lauparr.pplanner.server.dao.DaoProjectMenuItem;
import fr.lauparr.pplanner.server.entities.ProjectMenuItem;
import fr.lauparr.pplanner.server.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SrvProjectMenuItem {

	@Autowired
	private DaoProjectMenuItem daoProjectMenuItem;

	public ProjectMenuItem findById(final String projectId) {
		return this.daoProjectMenuItem.findById(projectId).orElseThrow(() -> new NotFoundException("Le menu n'existe pas"));
	}

	public List<ProjectMenuItem> findAllWorkspaceByProjectId(final String projectId) {
		return this.daoProjectMenuItem.findAllWorkspaceByProjectId(projectId);
	}

	public ProjectMenuItem findFirstWorkspaceByProjectId(final String projectId) {
		final List<ProjectMenuItem> allWorkspaceByProjectId = this.findAllWorkspaceByProjectId(projectId);
		if (allWorkspaceByProjectId.size() > 0) {
			return allWorkspaceByProjectId.get(0);
		}
		return null;
	}
}
