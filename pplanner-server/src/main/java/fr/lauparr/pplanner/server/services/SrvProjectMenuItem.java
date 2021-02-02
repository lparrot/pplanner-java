package fr.lauparr.pplanner.server.services;

import fr.lauparr.pplanner.server.controllers.CtrlProjectMenuItem;
import fr.lauparr.pplanner.server.dao.DaoProject;
import fr.lauparr.pplanner.server.dao.DaoProjectMenuItem;
import fr.lauparr.pplanner.server.entities.Project;
import fr.lauparr.pplanner.server.entities.ProjectMenuItem;
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

	public ProjectMenuItem findById(final String itemId) {
		return this.daoProjectMenuItem.findById(itemId, UtilsSecurity.getCurrentUserOrThrowUnauthorizedException()).orElseThrow(NotFoundException::new);
	}

	public ProjectMenuItem findByIdAndProjectId(final String itemId, final String projectId) {
		return this.daoProjectMenuItem.findByIdAndProjectId(itemId, projectId, UtilsSecurity.getCurrentUserOrThrowUnauthorizedException()).orElseThrow(NotFoundException::new);
	}

	public List<ProjectMenuItem> findAllWorkspaceByProjectId(final String projectId) {
		return this.daoProjectMenuItem.findAllWorkspaceByProjectId(projectId, UtilsSecurity.getCurrentUserOrThrowUnauthorizedException());
	}

	public ProjectMenuItem createItemByType(final ProjectMenuItemType menuItemType, final CtrlProjectMenuItem.ParamsCreateItemByType params) {
		final Project project = this.daoProject.findByIdAndCreatorAndMember(params.getProjectId(), UtilsSecurity.getCurrentUserOrThrowUnauthorizedException())
			.orElseThrow(NotFoundException::new);

		final BigDecimal orderIndex = this.daoProjectMenuItem.getMaxByTypeAndProjectId(menuItemType, params.getProjectId());

		final ProjectMenuItem projectMenuItem = ProjectMenuItem.builder()
			.project(project)
			.type(menuItemType)
			.name(params.getName())
			.orderIndex(orderIndex == null ? BigDecimal.ONE : orderIndex.add(BigDecimal.ONE))
			.build();

		switch (menuItemType) {
			case WORKSPACE:
				break;
			case FOLDER:
			case LIST:
				final ProjectMenuItem parent = this.daoProjectMenuItem.findByIdAndProjectId(params.getParentId(), params.getProjectId(), UtilsSecurity.getCurrentUserOrThrowUnauthorizedException()).orElseThrow(NotFoundException::new);
				projectMenuItem.addParent(parent);
				break;
			default:
				throw new NotFoundException();
		}
		return this.daoProjectMenuItem.save(projectMenuItem);
	}
}
