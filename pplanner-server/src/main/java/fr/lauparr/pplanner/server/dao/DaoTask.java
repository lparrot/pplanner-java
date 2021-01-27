package fr.lauparr.pplanner.server.dao;

import fr.lauparr.pplanner.server.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DaoTask extends JpaRepository<Task, String>, JpaSpecificationExecutor<Task> {

	@Query("select t from Task t where t.item.id = :itemId and t.deletedAt is null")
	List<Task> findAllTasksByMenuItemId(@Param("itemId") String itemId);

}
