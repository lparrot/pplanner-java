package fr.lauparr.pplanner.server.projections;

import fr.lauparr.pplanner.server.enums.TaskStatusType;

public interface ProjTaskStatus {

	String getId();

	String getName();

	String getColor();

	TaskStatusType getType();

}
