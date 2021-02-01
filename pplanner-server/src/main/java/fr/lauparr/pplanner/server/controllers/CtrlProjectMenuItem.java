package fr.lauparr.pplanner.server.controllers;

import fr.lauparr.pplanner.server.entities.ProjectMenuItem;
import fr.lauparr.pplanner.server.entities.User;
import fr.lauparr.pplanner.server.enums.ProjectMenuItemType;
import fr.lauparr.pplanner.server.exceptions.NotFoundException;
import fr.lauparr.pplanner.server.projections.ProjMenuItem;
import fr.lauparr.pplanner.server.services.SrvProjectMenuItem;
import fr.lauparr.pplanner.server.services.utils.SrvJpaUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class CtrlProjectMenuItem {

	@Autowired
	private SrvJpaUtils srvJpaUtils;
	@Autowired
	private SrvProjectMenuItem srvProjectMenuItem;

	@GetMapping("/{itemId}")
	public ProjMenuItem findById(@PathVariable final String itemId, @AuthenticationPrincipal final User user) {
		return this.srvJpaUtils.convertToDto(this.srvProjectMenuItem.findById(itemId, user), ProjMenuItem.class);
	}

	@GetMapping("/{itemId}/projects/{projectId}")
	public ProjMenuItem findByIdAndProjectId(@PathVariable final String itemId, @PathVariable final String projectId, @AuthenticationPrincipal final User user) {
		return this.srvJpaUtils.convertToDto(this.srvProjectMenuItem.findByIdAndProjectId(itemId, projectId, user), ProjMenuItem.class);
	}

	@GetMapping("/projects/{projectId}/first")
	public ProjMenuItem findFirstWorkspaceByProjectId(@PathVariable final String projectId, @AuthenticationPrincipal final User user) {
		return this.srvJpaUtils.convertToDto(this.srvProjectMenuItem.findFirstWorkspaceByProjectId(projectId, user), ProjMenuItem.class);
	}

	@PostMapping("/{itemType}")
	public String createItemByType(@PathVariable final String itemType, @RequestBody final ParamsCreateItemByType params) {
		try {
			final ProjectMenuItemType menuItemType = ProjectMenuItemType.valueOf(itemType.toUpperCase());
			final ProjectMenuItem item = this.srvProjectMenuItem.createItemByType(menuItemType, params);
			return item.getId();
		} catch (final IllegalArgumentException e) {
			throw new NotFoundException();
		}
	}

	@Data
	public static class ParamsCreateItemByType {
		private String name;
		private String projectId;
	}
}
