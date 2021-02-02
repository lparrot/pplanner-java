package fr.lauparr.pplanner.server.dao;

import fr.lauparr.pplanner.server.entities.Group;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface DaoGroup extends JpaRepository<Group, String>, JpaSpecificationExecutor<Group> {

	@Cacheable("defaultGroupCache")
	@Query("select g from Group g where g.defaultGroup = true and g.deletedAt is null")
	Group findDefaultGroup();
}
