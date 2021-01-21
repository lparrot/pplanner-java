package fr.lauparr.pplanner.server.dao;

import fr.lauparr.pplanner.server.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DaoProject extends JpaRepository<Project, String>, JpaSpecificationExecutor<Project> {
}
