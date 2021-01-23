package fr.lauparr.pplanner.server.dao;

import fr.lauparr.pplanner.server.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DaoFavorite extends JpaRepository<Favorite, String>, JpaSpecificationExecutor<Favorite> {

	@Query("select f from Favorite f where f.user.id = :userId and f.menuItem.project.id = :projectId and f.dateSuppression is null")
	List<Favorite> findAllFavoriteByUserIdAndProjectId(@Param("userId") String userId, @Param("projectId") String projectId);

	@Query("select case when count(f)> 0 then true else false end from Favorite f where f.user.id = :userId and f.menuItem.id = :menuId")
	boolean existsByUserIdAndMenuId(@Param("userId") String userId, @Param("menuId") String menuId);
}
