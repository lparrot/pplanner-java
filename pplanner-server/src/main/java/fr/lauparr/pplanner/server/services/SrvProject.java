package fr.lauparr.pplanner.server.services;

import fr.lauparr.pplanner.server.dao.DaoProject;
import fr.lauparr.pplanner.server.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SrvProject {

	@Autowired
	private DaoProject daoProject;

	public Page<Project> findAllProject(final Pageable page) {
		return this.daoProject.findAll(page);
	}
}
