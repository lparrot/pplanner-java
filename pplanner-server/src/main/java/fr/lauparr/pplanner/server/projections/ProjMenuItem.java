package fr.lauparr.pplanner.server.projections;

import fr.lauparr.pplanner.server.enums.ProjectMenuItemType;

import java.util.List;

public interface ProjMenuItem {

	String getId();

	String getName();

	ProjectMenuItemType getType();

	List<ProjMenuItem> getChildren();

}
