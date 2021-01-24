package fr.lauparr.pplanner.server.controllers;

import fr.lauparr.pplanner.server.projections.ProjTask;
import fr.lauparr.pplanner.server.services.SrvTask;
import fr.lauparr.pplanner.server.services.utils.SrvJpaUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class CtrlTask {

	@Autowired
	private SrvJpaUtils srvJpaUtils;
	@Autowired
	private SrvTask srvTask;

	@GetMapping("/items/{itemId}")
	public List<ProjTask> findAllTasksByMenuItemId(@PathVariable final String itemId) {
		return this.srvJpaUtils.convertListDto(this.srvTask.findAllTasksByMenuItemId(itemId), ProjTask.class);
	}

	@PostMapping("/items/{itemId}")
	public void createTask(@RequestBody final ParamsCreateTask params, @PathVariable final String itemId) {
		this.srvTask.createTask(params, itemId);
	}

	@Data
	public static class ParamsCreateTask {
		private String name;
		private String description;
	}
}
