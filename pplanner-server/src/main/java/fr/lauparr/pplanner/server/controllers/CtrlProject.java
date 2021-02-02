package fr.lauparr.pplanner.server.controllers;

import fr.lauparr.pplanner.server.projections.ProjMenuItem;
import fr.lauparr.pplanner.server.projections.ProjProject;
import fr.lauparr.pplanner.server.services.SrvProject;
import fr.lauparr.pplanner.server.services.SrvProjectMenuItem;
import fr.lauparr.pplanner.server.services.utils.SrvJpaUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	public List<ProjProject> findAllByCreatorAndMember() {
		return this.srvJpaUtils.convertListDto(this.srvProject.findAllByCreatorAndMember(), ProjProject.class);
	}

	@GetMapping("/{projectId}")
	public ProjProject findById(@PathVariable final String projectId) {
		return this.srvJpaUtils.convertToDto(this.srvProject.findById(projectId), ProjProject.class);
	}

	@GetMapping("/{id}/workspaces")
	public List<ProjMenuItem> findAllWorkspaceByProjectId(@PathVariable final String id) {
		return this.srvJpaUtils.convertListDto(this.srvProjectMenuItem.findAllWorkspaceByProjectId(id), ProjMenuItem.class);
	}

	@PostMapping
	public void createProject(@RequestBody final ParamsCreateProject params) {
		this.srvProject.createProject(params);
	}

	@Data
	public static class ParamsCreateProject {
		private String name;
	}

}
