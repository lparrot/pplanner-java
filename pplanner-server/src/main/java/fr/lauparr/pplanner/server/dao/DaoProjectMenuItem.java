package fr.lauparr.pplanner.server.dao;

import fr.lauparr.pplanner.server.entities.ProjectMenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DaoProjectMenuItem extends JpaRepository<ProjectMenuItem, String>, JpaSpecificationExecutor<ProjectMenuItem> {

	@Query("select i from ProjectMenuItem i where i.project.id = :projectId and i.type = 'WORKSPACE' and i.deletedAt is null order by i.orderIndex")
	List<ProjectMenuItem> findAllWorkspaceByProjectId(@Param("projectId") String projectId);
}
