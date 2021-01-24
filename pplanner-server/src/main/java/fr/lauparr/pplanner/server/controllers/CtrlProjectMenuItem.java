package fr.lauparr.pplanner.server.controllers;

import fr.lauparr.pplanner.server.projections.ProjMenuItem;
import fr.lauparr.pplanner.server.services.SrvProjectMenuItem;
import fr.lauparr.pplanner.server.services.utils.SrvJpaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
public class CtrlProjectMenuItem {

	@Autowired
	private SrvJpaUtils srvJpaUtils;
	@Autowired
	private SrvProjectMenuItem srvProjectMenuItem;

	@GetMapping("/{projectId}")
	public ProjMenuItem findById(@PathVariable final String projectId) {
		return this.srvJpaUtils.convertToDto(this.srvProjectMenuItem.findById(projectId), ProjMenuItem.class);
	}

	@GetMapping("/projects/{projectId}/first")
	public ProjMenuItem findFirstWorkspaceByProjectId(@PathVariable final String projectId) {
		return this.srvJpaUtils.convertToDto(this.srvProjectMenuItem.findFirstWorkspaceByProjectId(projectId), ProjMenuItem.class);
	}

}
