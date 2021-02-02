package fr.lauparr.pplanner.server.services;

import fr.lauparr.pplanner.server.controllers.CtrlFavorite;
import fr.lauparr.pplanner.server.dao.DaoFavorite;
import fr.lauparr.pplanner.server.dao.DaoProjectMenuItem;
import fr.lauparr.pplanner.server.entities.Favorite;
import fr.lauparr.pplanner.server.entities.ProjectMenuItem;
import fr.lauparr.pplanner.server.exceptions.NotFoundException;
import fr.lauparr.pplanner.server.utils.UtilsSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SrvFavorite {

	@Autowired
	private DaoFavorite daoFavorite;
	@Autowired
	private DaoProjectMenuItem daoProjectMenuItem;

	public List<Favorite> findAllFavoriteByProjectId(final String projectId) {
		return this.daoFavorite.findAllFavoriteByProjectId(projectId, UtilsSecurity.getCurrentUserOrThrowUnauthorizedException());
	}

	public void createFavorite(final CtrlFavorite.ParamsCreateFavorite params) {
		if (this.daoFavorite.existsByMenuId(params.getMenuId(), UtilsSecurity.getCurrentUserOrThrowUnauthorizedException())) {
			return;
		}

		final ProjectMenuItem projectMenuItem = this.daoProjectMenuItem.findById(params.getMenuId()).orElseThrow(NotFoundException::new);

		final Favorite favorite = Favorite.builder().menuItem(projectMenuItem).build();
		this.daoFavorite.save(favorite);
	}

	public void deleteFavorite(final String favoriteId) {
		final Favorite favorite = this.daoFavorite.findById(favoriteId).orElseThrow(NotFoundException::new);

		favorite.delete();
		this.daoFavorite.save(favorite);
	}
}
