package fr.lauparr.pplanner.server.dao;

import fr.lauparr.pplanner.server.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface DaoUser extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

	Optional<User> findByEmailAndDateSuppressionIsNull(String username);
}
