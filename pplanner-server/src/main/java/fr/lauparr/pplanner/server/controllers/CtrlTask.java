package fr.lauparr.pplanner.server.controllers;

import fr.lauparr.pplanner.server.projections.ProjTask;
import fr.lauparr.pplanner.server.services.SrvTask;
import fr.lauparr.pplanner.server.services.utils.SrvJpaUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class CtrlTask {

	@Autowired
	private SrvJpaUtils srvJpaUtils;
	@Autowired
	private SrvTask srvTask;

	@GetMapping("/items/{itemId}")
	public Map<String, List<ProjTask>> findAllTasksByMenuItemId(@PathVariable final String itemId) {
		return this.srvTask.findAllTasksByMenuItemIdGroupedByStatus(itemId);
	}

	@PostMapping("/items/{itemId}")
	public void createTask(@RequestBody final ParamsCreateTask params, @PathVariable final String itemId) {
		this.srvTask.createTask(params, itemId);
	}

	@PutMapping("/{taskId}/status/{statusId}")
	public void updateTaskStatus(@PathVariable final String taskId, @PathVariable final String statusId) {
		this.srvTask.updateTaskStatus(taskId, statusId);
	}

	@Data
	public static class ParamsCreateTask {
		private String name;
		private String description;
	}
}
