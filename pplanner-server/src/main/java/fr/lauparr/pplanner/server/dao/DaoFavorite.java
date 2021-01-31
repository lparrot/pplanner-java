package fr.lauparr.pplanner.server.dao;

import fr.lauparr.pplanner.server.entities.Favorite;
import fr.lauparr.pplanner.server.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DaoFavorite extends JpaRepository<Favorite, String>, JpaSpecificationExecutor<Favorite> {

	@Query("select f from Favorite f where f.menuItem.project.id = ?1 and f.createdBy = ?2 and f.deletedAt is null")
	List<Favorite> findAllFavoriteByProjectId(String projectId, User user);

	@Query("select case when count(f)> 0 then true else false end from Favorite f where f.menuItem.id = ?1 and f.createdBy = ?2 and f.deletedAt is null")
	boolean existsByMenuId(String menuId, User user);
}
