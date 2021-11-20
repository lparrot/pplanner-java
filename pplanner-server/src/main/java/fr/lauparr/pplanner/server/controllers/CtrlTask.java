package fr.lauparr.pplanner.server.controllers;

import fr.lauparr.pplanner.server.entities.ProjectMenuItem;
import fr.lauparr.pplanner.server.entities.Task;
import fr.lauparr.pplanner.server.entities.TaskStatus;
import fr.lauparr.pplanner.server.projections.ProjTask;
import fr.lauparr.pplanner.server.services.SrvTask;
import fr.lauparr.pplanner.server.services.utils.SrvJpaUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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
	public void createTask(@Valid @RequestBody final ParamsCreateTask params, @PathVariable("itemId") final ProjectMenuItem projectMenuItem) {
		this.srvTask.createTask(params, projectMenuItem);
	}

	@PutMapping("/{taskId}/status/{statusId}")
	public void updateTaskStatus(@PathVariable(name = "taskId") final Task task, @PathVariable(name = "statusId", required = false) final TaskStatus taskStatus) {
		this.srvTask.updateTaskStatus(task, taskStatus);
	}

	@Data
	public static class ParamsCreateTask {
		@NotBlank
		private String name;
		@NotBlank
		private String description;

		private String statusId;
	}
}
