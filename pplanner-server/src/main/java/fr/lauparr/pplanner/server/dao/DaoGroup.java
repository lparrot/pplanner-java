package fr.lauparr.pplanner.server.dao;

import fr.lauparr.pplanner.server.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DaoGroup extends JpaRepository<Group, String>, JpaSpecificationExecutor<Group> {


}
