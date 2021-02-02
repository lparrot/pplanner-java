package fr.lauparr.pplanner.server.services;

import fr.lauparr.pplanner.server.controllers.CtrlProject;
import fr.lauparr.pplanner.server.dao.DaoProject;
import fr.lauparr.pplanner.server.entities.Project;
import fr.lauparr.pplanner.server.exceptions.NotFoundException;
import fr.lauparr.pplanner.server.utils.UtilsSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SrvProject {

	@Autowired
	private DaoProject daoProject;

	public List<Project> findAllByCreatorAndMember() {
		return this.daoProject.findAllByCreatorAndMember(UtilsSecurity.getCurrentUserOrThrowUnauthorizedException());
	}

	public Project findById(final String projectId) {
		return this.daoProject.findByIdAndCreatorAndMember(projectId, UtilsSecurity.getCurrentUserOrThrowUnauthorizedException()).orElseThrow(NotFoundException::new);
	}

	@Transactional
	public void createProject(final CtrlProject.ParamsCreateProject params) {
		final Project project = Project.builder().name(params.getName()).build();
		this.daoProject.save(project);
	}
}
