package fr.lauparr.pplanner.server.projections;

import org.springframework.beans.factory.annotation.Value;

public interface ProjFavorite {

	String getId();

	@Value("#{target.createdBy?.id}")
	String getUserId();

	@Value("#{target.menuItem.id}")
	String getMenuItemId();

	@Value("#{target.menuItem.name}")
	String getName();

}
