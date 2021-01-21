package fr.lauparr.pplanner.server.services;

import fr.lauparr.pplanner.server.dao.DaoFavorite;
import fr.lauparr.pplanner.server.entities.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SrvFavorite {

	@Autowired
	private DaoFavorite daoFavorite;

	public List<Favorite> findAllFavoriteByUserId(final String id) {
		return this.daoFavorite.findAllFavoriteByUserId(id);
	}
}
