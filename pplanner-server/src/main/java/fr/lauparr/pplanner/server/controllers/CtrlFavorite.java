package fr.lauparr.pplanner.server.controllers;

import fr.lauparr.pplanner.server.projections.ProjFavorite;
import fr.lauparr.pplanner.server.services.SrvFavorite;
import fr.lauparr.pplanner.server.services.utils.SrvJpaUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class CtrlFavorite {

	@Autowired
	private SrvFavorite srvFavorite;
	@Autowired
	private SrvJpaUtils srvJpaUtils;

	@GetMapping("/users/{userId}/projects/{projectId}")
	public List<ProjFavorite> findAllFavoriteByUserIdAndProjectId(@PathVariable final String userId, @PathVariable final String projectId) {
		return this.srvJpaUtils.convertListDto(this.srvFavorite.findAllFavoriteByUserIdAndProjectId(userId, projectId), ProjFavorite.class);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createFavorite(@RequestBody final ParamsCreateFavorite favorite) {
		this.srvFavorite.createFavorite(favorite);
	}

	@DeleteMapping("/{favoriteId}")
	public void deleteFavorite(@PathVariable final String favoriteId) {
		this.srvFavorite.deleteFavorite(favoriteId);
	}

	@Data
	public static class ParamsCreateFavorite {
		private String userId;
		private String menuId;
	}

}
