package fr.lauparr.pplanner.server.dao;

import fr.lauparr.pplanner.server.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DaoFavorite extends JpaRepository<Favorite, String>, JpaSpecificationExecutor<Favorite> {

	@Query("select f from Favorite f where f.user.id = :id and f.dateSuppression is null")
	List<Favorite> findAllFavoriteByUserId(@Param("id") String id);

}
