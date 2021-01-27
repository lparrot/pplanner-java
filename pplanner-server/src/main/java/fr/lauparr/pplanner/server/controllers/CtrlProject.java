package fr.lauparr.pplanner.server.controllers;

import fr.lauparr.pplanner.server.projections.ProjMenuItem;
import fr.lauparr.pplanner.server.projections.ProjProject;
import fr.lauparr.pplanner.server.services.SrvProject;
import fr.lauparr.pplanner.server.services.SrvProjectMenuItem;
import fr.lauparr.pplanner.server.services.utils.SrvJpaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class CtrlProject {

	@Autowired
	private SrvJpaUtils srvJpaUtils;
	@Autowired
	private SrvProject srvProject;
	@Autowired
	private SrvProjectMenuItem srvProjectMenuItem;

	@GetMapping
	public Page<ProjProject> findAll(final Pageable page) {
		return this.srvJpaUtils.convertPageDto(this.srvProject.findAllProject(page), ProjProject.class);
	}

	@GetMapping("/{projectId}")
	public ProjProject findAll(@PathVariable final String projectId) {
		return this.srvJpaUtils.convertToDto(this.srvProject.findById(projectId), ProjProject.class);
	}

	@GetMapping("/{id}/workspaces")
	public List<ProjMenuItem> findAllWorkspaceByProjectId(@PathVariable final String id) {
		return this.srvJpaUtils.convertListDto(this.srvProjectMenuItem.findAllWorkspaceByProjectId(id), ProjMenuItem.class);
	}

}
