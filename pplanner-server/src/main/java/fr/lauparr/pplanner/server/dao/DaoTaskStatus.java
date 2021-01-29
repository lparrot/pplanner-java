package fr.lauparr.pplanner.server.dao;

import fr.lauparr.pplanner.server.entities.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DaoTaskStatus extends JpaRepository<TaskStatus, String>, JpaSpecificationExecutor<TaskStatus> {

	@Query("select ts from TaskStatus ts where ts.item.id = :itemId and ts.deletedAt is null order by ts.orderIndex")
	List<TaskStatus> findStatusByItemId(@Param("itemId") String itemId);
}
