package fr.lauparr.pplanner.server.services;

import fr.lauparr.pplanner.server.controllers.CtrlProjectMenuItem;
import fr.lauparr.pplanner.server.dao.DaoProject;
import fr.lauparr.pplanner.server.dao.DaoProjectMenuItem;
import fr.lauparr.pplanner.server.entities.Project;
import fr.lauparr.pplanner.server.entities.ProjectMenuItem;
import fr.lauparr.pplanner.server.entities.User;
import fr.lauparr.pplanner.server.enums.ProjectMenuItemType;
import fr.lauparr.pplanner.server.exceptions.NotFoundException;
import fr.lauparr.pplanner.server.utils.UtilsSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SrvProjectMenuItem {

	@Autowired
	private DaoProjectMenuItem daoProjectMenuItem;
	@Autowired
	private DaoProject daoProject;

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

	public ProjectMenuItem createItemByType(final ProjectMenuItemType menuItemType, final CtrlProjectMenuItem.ParamsCreateItemByType params) {
		final Project project = this.daoProject.findByIdAndCreatorAndMember(params.getProjectId(), UtilsSecurity.getCurrentUserOrThrowUnauthorizedException())
			.orElseThrow(NotFoundException::new);

		final ProjectMenuItem projectMenuItem = ProjectMenuItem.builder()
			.project(project)
			.type(menuItemType)
			.name(params.getName())
			.orderIndex(this.daoProjectMenuItem.getMaxByTypeAndProjectId(menuItemType, params.getProjectId()).add(BigDecimal.ONE))
			.build();
		return this.daoProjectMenuItem.save(projectMenuItem);
	}
}
