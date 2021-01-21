package fr.lauparr.pplanner.server.services.utils;

import fr.lauparr.pplanner.server.dao.DaoFavorite;
import fr.lauparr.pplanner.server.dao.DaoGroup;
import fr.lauparr.pplanner.server.dao.DaoProject;
import fr.lauparr.pplanner.server.dao.DaoUser;
import fr.lauparr.pplanner.server.entities.*;
import fr.lauparr.pplanner.server.enums.ProjectMenuItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SrvDatabaseInitializer {

	@Autowired
	private DaoProject daoProject;
	@Autowired
	private DaoUser daoUser;
	@Autowired
	private DaoFavorite daoFavorite;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private DaoGroup daoGroup;

	public void initialize() {
		if (this.daoProject.count() <= 0) {
			// Groups
			final Group admin = Group.builder().name("Administrateur").role(Role.ADMIN).build();
			final Group user = Group.builder().name("Invité").role(Role.USER).build();
			this.daoGroup.save(admin);
			this.daoGroup.save(user);

			// Users
			final User root = User.builder().email("kestounet@gmail.com").group(admin).build();
			root.setPassword(this.passwordEncoder.encode("123"));
			this.daoUser.save(root);

			// Projects
			final Project cdadr = Project.builder().name("CDAD-R").build();

			// Menu items
			final ProjectMenuItem ccs = ProjectMenuItem.builder().name("CCS").type(ProjectMenuItemType.WORKSPACE).project(cdadr).build();
			final ProjectMenuItem sprints = ProjectMenuItem.builder().name("Sprints").type(ProjectMenuItemType.FOLDER).project(cdadr).build();
			final ProjectMenuItem sprint1 = ProjectMenuItem.builder().name("Sprint 1").type(ProjectMenuItemType.LIST).project(cdadr).build();
			final ProjectMenuItem sprint2 = ProjectMenuItem.builder().name("Sprint 2").type(ProjectMenuItemType.LIST).project(cdadr).build();
			final ProjectMenuItem sprint3 = ProjectMenuItem.builder().name("Sprint 3").type(ProjectMenuItemType.LIST).project(cdadr).build();

			cdadr.addMenuItem(ccs);

			ccs.addChild(sprints);
			sprints.addChild(sprint1);
			sprints.addChild(sprint2);
			sprints.addChild(sprint3);

			this.daoProject.save(cdadr);

			// Favorites
			final Favorite favorite1 = Favorite.builder().user(root).menuItem(ccs).build();
			final Favorite favorite2 = Favorite.builder().user(root).menuItem(sprints).build();
			final Favorite favorite3 = Favorite.builder().user(root).menuItem(sprint3).build();

			this.daoFavorite.saveAll(Arrays.asList(favorite1, favorite2, favorite3));
		}
	}

}
