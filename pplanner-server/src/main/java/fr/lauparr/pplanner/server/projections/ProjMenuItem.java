package fr.lauparr.pplanner.server.projections;

import fr.lauparr.pplanner.server.enums.ProjectMenuItemType;

import java.math.BigDecimal;
import java.util.List;

public interface ProjMenuItem {

	String getId();

	String getName();

	ProjectMenuItemType getType();

	BigDecimal getOrderIndex();

	List<ProjMenuItem> getChildren();

}
