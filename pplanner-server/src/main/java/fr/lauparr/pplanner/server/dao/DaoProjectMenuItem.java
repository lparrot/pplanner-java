package fr.lauparr.pplanner.server.dao;

import fr.lauparr.pplanner.server.entities.ProjectMenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DaoProjectMenuItem extends JpaRepository<ProjectMenuItem, String>, JpaSpecificationExecutor<ProjectMenuItem> {

	@Query("select m from ProjectMenuItem  m where m.project.id = :id and m.type = 'WORKSPACE' and m.dateSuppression is null")
	List<ProjectMenuItem> findAllWorkspaceByProjectId(@Param("id") String id);
}
