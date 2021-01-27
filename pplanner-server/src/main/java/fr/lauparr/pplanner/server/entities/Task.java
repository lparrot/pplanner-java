package fr.lauparr.pplanner.server.entities;

import fr.lauparr.pplanner.server.entities.abstracts.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tasks")
@EqualsAndHashCode(callSuper = true)
public class Task extends BaseEntity {

	private String name;

	private String description;

	@ManyToOne
	private ProjectMenuItem item;

	@ManyToOne
	private TaskStatus status;

	@Builder
	public Task(final String name, final String description, final ProjectMenuItem item, final TaskStatus status) {
		this.name = name;
		this.description = description;
		this.item = item;
		this.status = status;
	}
}
