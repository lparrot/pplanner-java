package fr.lauparr.pplanner.server.dao;

import fr.lauparr.pplanner.server.entities.Project;
import fr.lauparr.pplanner.server.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DaoProject extends JpaRepository<Project, String>, JpaSpecificationExecutor<Project> {
	// TODO : member
	@Query("select p from Project p where p.createdBy = ?1")
	List<Project> findAllByCreatorAndMember(User user);

	// TODO : member
	@Query("select p from Project p where p.id = ?1 and p.createdBy = ?2")
	Optional<Project> findByIdAndCreatorAndMember(String projectId, User user);
}
