package fr.lauparr.pplanner.server.controllers;

import fr.lauparr.pplanner.server.projections.ProjTaskStatus;
import fr.lauparr.pplanner.server.services.SrvTaskStatus;
import fr.lauparr.pplanner.server.services.utils.SrvJpaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/task_status")
public class CtrlTaskStatus {

	@Autowired
	private SrvJpaUtils srvJpaUtils;
	@Autowired
	private SrvTaskStatus srvTaskStatus;

	@GetMapping("/items/{itemId}")
	public List<ProjTaskStatus> findStatusByItemId(@PathVariable final String itemId) {
		return this.srvJpaUtils.convertListDto(this.srvTaskStatus.findStatusByItemId(itemId), ProjTaskStatus.class);
	}

}
