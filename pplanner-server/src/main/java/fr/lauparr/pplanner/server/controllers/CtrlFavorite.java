package fr.lauparr.pplanner.server.controllers;

import fr.lauparr.pplanner.server.projections.ProjFavorite;
import fr.lauparr.pplanner.server.services.SrvFavorite;
import fr.lauparr.pplanner.server.services.utils.SrvJpaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class CtrlFavorite {

	@Autowired
	private SrvFavorite srvFavorite;
	@Autowired
	private SrvJpaUtils srvJpaUtils;

	@GetMapping("/users/{id}")
	public List<ProjFavorite> findAllFavoriteByUserId(@PathVariable final String id) {
		return this.srvJpaUtils.convertListDto(this.srvFavorite.findAllFavoriteByUserId(id), ProjFavorite.class);
	}

}
