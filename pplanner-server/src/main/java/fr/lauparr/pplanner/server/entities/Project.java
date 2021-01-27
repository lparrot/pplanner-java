package fr.lauparr.pplanner.server.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import fr.lauparr.pplanner.server.entities.abstracts.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "projects")
@EqualsAndHashCode(callSuper = true)
public class Project extends BaseEntity {

	private String name;

	@OneToMany(mappedBy = "project", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
	@JsonBackReference("project_menu_item")
	private List<ProjectMenuItem> items = new ArrayList<>();

	@Builder
	public Project(final String name) {
		this.name = name;
	}

	public Project addMenuItem(final ProjectMenuItem item) {
		this.items.add(item);
		item.setProject(this);
		return this;
	}

}
