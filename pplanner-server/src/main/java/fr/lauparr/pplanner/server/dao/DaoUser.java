package fr.lauparr.pplanner.server.dao;

import fr.lauparr.pplanner.server.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DaoUser extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

	@Query("select u from User u join fetch u.group where u.email = ?1 and u.deletedAt is null")
	Optional<User> findByEmailAndDeletedAtIsNull(String username);
}
