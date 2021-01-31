package fr.lauparr.pplanner.server.services;

import fr.lauparr.pplanner.server.controllers.CtrlProject;
import fr.lauparr.pplanner.server.dao.DaoProject;
import fr.lauparr.pplanner.server.entities.Project;
import fr.lauparr.pplanner.server.entities.User;
import fr.lauparr.pplanner.server.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SrvProject {

	@Autowired
	private DaoProject daoProject;

	public List<Project> findAllByCreatorAndMember(final User user) {
		return this.daoProject.findAllByCreatorAndMember(user);
	}

	public Project findById(final String projectId) {
		return this.daoProject.findById(projectId).orElseThrow(NotFoundException::new);
	}

	@Transactional
	public void createProject(final CtrlProject.ParamsCreateProject params) {
		final Project project = Project.builder().name(params.getName()).build();
		this.daoProject.save(project);
	}
}
