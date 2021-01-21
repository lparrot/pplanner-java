package fr.lauparr.pplanner.server.services;

import fr.lauparr.pplanner.server.dao.DaoProjectMenuItem;
import fr.lauparr.pplanner.server.entities.ProjectMenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SrvProjectMenuItem {

	@Autowired
	private DaoProjectMenuItem daoProjectMenuItem;

	public List<ProjectMenuItem> findAllWorkspaceByProjectId(final String id) {
		return this.daoProjectMenuItem.findAllWorkspaceByProjectId(id);
	}
}
