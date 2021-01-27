package fr.lauparr.pplanner.server.services;

import fr.lauparr.pplanner.server.controllers.CtrlFavorite;
import fr.lauparr.pplanner.server.dao.DaoFavorite;
import fr.lauparr.pplanner.server.dao.DaoProjectMenuItem;
import fr.lauparr.pplanner.server.dao.DaoUser;
import fr.lauparr.pplanner.server.entities.Favorite;
import fr.lauparr.pplanner.server.entities.ProjectMenuItem;
import fr.lauparr.pplanner.server.entities.User;
import fr.lauparr.pplanner.server.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SrvFavorite {

	@Autowired
	private DaoFavorite daoFavorite;
	@Autowired
	private DaoUser daoUser;
	@Autowired
	private DaoProjectMenuItem daoProjectMenuItem;

	public List<Favorite> findAllFavoriteByUserIdAndProjectId(final String userId, final String projectId) {
		return this.daoFavorite.findAllFavoriteByUserIdAndProjectId(userId, projectId);
	}

	public void createFavorite(final CtrlFavorite.ParamsCreateFavorite params) {
		if (this.daoFavorite.existsByUserIdAndMenuId(params.getUserId(), params.getMenuId())) {
			return;
		}

		final User user = this.daoUser.findById(params.getUserId()).orElseThrow(() -> new NotFoundException("L'utilisateur n'existe pas"));
		final ProjectMenuItem projectMenuItem = this.daoProjectMenuItem.findById(params.getMenuId()).orElseThrow(() -> new NotFoundException("Le menu n'existe pas"));

		final Favorite favorite = Favorite.builder().user(user).menuItem(projectMenuItem).build();
		this.daoFavorite.save(favorite);
	}

	public void deleteFavorite(final String favoriteId) {
		final Favorite favorite = this.daoFavorite.findById(favoriteId).orElseThrow(() -> new NotFoundException("Le favoris n'existe pas"));

		favorite.delete();
		this.daoFavorite.save(favorite);
	}
}
