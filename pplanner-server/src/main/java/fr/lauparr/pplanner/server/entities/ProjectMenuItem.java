package fr.lauparr.pplanner.server.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import fr.lauparr.pplanner.server.entities.abstracts.BaseEntity;
import fr.lauparr.pplanner.server.enums.ProjectMenuItemType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "project_menu_items")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProjectMenuItem extends BaseEntity {

	private String name;

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

}
