package fr.lauparr.pplanner.server.projections;

import fr.lauparr.pplanner.server.enums.TaskStatusType;

import java.math.BigDecimal;

public interface ProjTaskStatus {

	String getId();

	String getName();

	String getColor();

	BigDecimal getOrderIndex();

	TaskStatusType getType();

}
