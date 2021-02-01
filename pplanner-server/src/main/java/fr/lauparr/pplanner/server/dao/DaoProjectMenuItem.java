package fr.lauparr.pplanner.server.dao;

import fr.lauparr.pplanner.server.entities.ProjectMenuItem;
import fr.lauparr.pplanner.server.entities.User;
import fr.lauparr.pplanner.server.enums.ProjectMenuItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DaoProjectMenuItem extends JpaRepository<ProjectMenuItem, String>, JpaSpecificationExecutor<ProjectMenuItem> {

	@Query("select i from ProjectMenuItem i where i.id = ?1 and i.createdBy = ?2")
	Optional<ProjectMenuItem> findById(String itemId, User user);

	@Query("select i from ProjectMenuItem  i where i.id = ?1 and i.project.id = ?2 and i.createdBy = ?3")
	Optional<ProjectMenuItem> findByIdAndProjectId(String itemId, String projectId, User user);

	@Query("select i from ProjectMenuItem i where i.project.id = ?1 and i.type = 'WORKSPACE' and i.deletedAt is null and i.createdBy = ?2 order by i.orderIndex")
	List<ProjectMenuItem> findAllWorkspaceByProjectId(String projectId, User user);

	@Query("select max(i.orderIndex) from ProjectMenuItem i where i.type = ?1 and i.project.id = ?2")
	BigDecimal getMaxByTypeAndProjectId(ProjectMenuItemType type, String projectId);
}
