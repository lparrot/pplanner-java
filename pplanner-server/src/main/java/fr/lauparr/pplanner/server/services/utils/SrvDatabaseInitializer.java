package fr.lauparr.pplanner.server.services.utils;

import com.github.javafaker.Faker;
import fr.lauparr.pplanner.server.dao.*;
import fr.lauparr.pplanner.server.entities.*;
import fr.lauparr.pplanner.server.enums.ProjectMenuItemType;
import fr.lauparr.pplanner.server.enums.TaskStatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.IntStream;

@Service
@Transactional
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
	@Autowired
	private DaoProjectMenuItem daoProjectMenuItem;
	@Autowired
	private SrvJpaUtils srvJpaUtils;
	@Autowired
	private DaoTaskStatus daoTaskStatus;
	@Autowired
	private DaoTask daoTask;

	public void initialize() {
		if (this.daoProject.count() <= 0) {
			final Faker faker = Faker.instance(Locale.FRENCH);

			// Groups
			final Group admin = Group.builder().name("Administrateur").role(Role.ADMIN).build();
			final Group user = Group.builder().name("Invité").role(Role.USER).build();
			this.daoGroup.save(admin);
			this.daoGroup.save(user);

			// Users
			final Member rootMember = Member.builder().email("kestounet@gmail.com").lastname("Parrot").firstname("Laurent").build();

			final User root = User.builder().email("kestounet@gmail.com").group(admin).member(rootMember).build();
			root.setPassword(this.passwordEncoder.encode("123"));
			this.daoUser.save(root);

			// Projects
			Project cdadr = Project.builder().name("CDAD-R").build();

			// Menu items
			final ProjectMenuItem ccs = ProjectMenuItem.builder().name("CCS").type(ProjectMenuItemType.WORKSPACE).project(cdadr).build();
			final ProjectMenuItem sprints = ProjectMenuItem.builder().name("Sprints").type(ProjectMenuItemType.FOLDER).project(cdadr).build();
			final ProjectMenuItem sprint1 = ProjectMenuItem.builder().name("Sprint 1").type(ProjectMenuItemType.LIST).project(cdadr).build();
			final ProjectMenuItem sprint2 = ProjectMenuItem.builder().name("Sprint 2").type(ProjectMenuItemType.LIST).project(cdadr).build();
			final ProjectMenuItem sprint3 = ProjectMenuItem.builder().name("Sprint 3").type(ProjectMenuItemType.LIST).project(cdadr).build();

			// Task status
			Arrays.asList(sprint1, sprint2, sprint3).forEach(projectMenuItem -> {
				final TaskStatus statusOpen = TaskStatus.builder().name("Ouverte").color("bg-gray-500 text-white").type(TaskStatusType.ACTIVE).build();
				final TaskStatus statusInProgress = TaskStatus.builder().name("En cours").color("bg-blue-500 text-white").type(TaskStatusType.ACTIVE).build();
				final TaskStatus statusReady = TaskStatus.builder().name("Prête").color("bg-purple-500 text-white").type(TaskStatusType.DONE).build();
				final TaskStatus statusClosed = TaskStatus.builder().name("Terminée").color("bg-green-500 text-white").type(TaskStatusType.CLOSED).build();

				projectMenuItem.addTaskStatus(statusOpen);
				projectMenuItem.addTaskStatus(statusInProgress);
				projectMenuItem.addTaskStatus(statusReady);
				projectMenuItem.addTaskStatus(statusClosed);
			});

			cdadr.addMenuItem(ccs);

			ccs.addChild(sprints);
			sprints.addChild(sprint1);
			sprints.addChild(sprint2);
			sprints.addChild(sprint3);

			cdadr = this.daoProject.save(cdadr);

			// Favorites
			final Favorite favorite1 = Favorite.builder().user(root).menuItem(ccs).build();
			final Favorite favorite2 = Favorite.builder().user(root).menuItem(sprints).build();
			final Favorite favorite3 = Favorite.builder().user(root).menuItem(sprint3).build();

			this.daoFavorite.saveAll(Arrays.asList(favorite1, favorite2, favorite3));

			final Random random = new Random();

			this.daoProjectMenuItem.findAll((path, query, criteriaBuilder) -> criteriaBuilder.equal(path.get("type"), ProjectMenuItemType.LIST)).forEach(projectMenuItem -> {
				final List<TaskStatus> allStatus = this.daoTaskStatus.findStatusByItemId(projectMenuItem.getId());

				IntStream.range(1, 10).forEach(value -> {
					final int index = random.nextInt(allStatus.size());

					final Task task = Task.builder().name("Tache " + value).description("Description tache " + value).status(allStatus.get(index)).assignee(rootMember).build();
					task.addComment(TaskComment.builder().author(rootMember).text(faker.lorem().paragraph()).build())
						.addComment(TaskComment.builder().author(rootMember).text(faker.lorem().paragraph()).build());
					projectMenuItem.addTask(task);
				});
			});

			cdadr = this.daoProject.findById(cdadr.getId()).orElse(null);

			if (cdadr != null) {
				cdadr.reindexOrder();
				this.daoProject.save(cdadr);
			}

		}
	}

}
