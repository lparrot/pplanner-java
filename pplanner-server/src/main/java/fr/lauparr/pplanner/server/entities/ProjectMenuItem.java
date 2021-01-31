package fr.lauparr.pplanner.server.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import fr.lauparr.pplanner.server.entities.abstracts.BaseEntity;
import fr.lauparr.pplanner.server.enums.ProjectMenuItemType;
import lombok.*;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "projects_menu_items")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class ProjectMenuItem extends BaseEntity {

	private String name;

	@SortNatural
	@Column(precision = 14, scale = 10)
	private BigDecimal orderIndex;

	@Enumerated(EnumType.STRING)
	private ProjectMenuItemType type;

	@ManyToOne
	@JsonManagedReference("project_menu_item")
	private Project project;

	@ManyToOne
	@JsonManagedReference("menu_item_children")
	private ProjectMenuItem parent;

	@OneToMany(mappedBy = "parent", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
	@JsonBackReference("menu_item_children")
	private List<ProjectMenuItem> children = new ArrayList<>();

	@OneToMany(mappedBy = "item", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
	@JsonBackReference("menu_item_status")
	private List<TaskStatus> status = new ArrayList<>();

	@OneToMany(mappedBy = "item", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
	@JsonBackReference("menu_item_tasks")
	private List<Task> tasks = new ArrayList<>();

	@Builder
	public ProjectMenuItem(final String name, final ProjectMenuItemType type, final Project project) {
		this.name = name;
		this.type = type;
		this.project = project;
	}

	public ProjectMenuItem addChild(final ProjectMenuItem item) {
		this.children.add(item);
		item.setParent(this);
		return this;
	}

	public ProjectMenuItem addParent(final ProjectMenuItem item) {
		item.getChildren().add(this);
		this.parent = item;
		return this;
	}

	public ProjectMenuItem addTask(final Task task) {
		this.getTasks().add(task);
		task.setItem(this);
		return this;
	}

	public ProjectMenuItem addTaskStatus(final TaskStatus status) {
		this.getStatus().add(status);
		status.setItem(this);
		return this;
	}

	public void reindexOrder() {
		this.reindexTaskOrder();
		this.reindexTaskStatusOrder();
		this.reindexChildrenOrder();
	}

	public void reindexTaskStatusOrder() {
		int i = 1;
		for (final TaskStatus taskStatus : this.status) {
			taskStatus.setOrderIndex(new BigDecimal(i++));
		}
	}

	public void reindexTaskOrder() {
		int i = 1;
		for (final Task task : this.tasks) {
			task.setOrderIndex(new BigDecimal(i++));
		}
	}

	public void reindexChildrenOrder() {
		int i = 1;
		for (final ProjectMenuItem child : this.children) {
			child.setOrderIndex(new BigDecimal(i++));
			child.reindexOrder();
		}
	}
}
