package fr.lauparr.pplanner.server.services;

import fr.lauparr.pplanner.server.dao.DaoTaskStatus;
import fr.lauparr.pplanner.server.entities.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SrvTaskStatus {
	@Autowired
	private DaoTaskStatus daoTaskStatus;

	public List<TaskStatus> findAllByItemId(final String itemId) {
		return this.daoTaskStatus.findAllByItemId(itemId);
	}
}
