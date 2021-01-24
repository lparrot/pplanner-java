package fr.lauparr.pplanner.server.entities;

import fr.lauparr.pplanner.server.entities.abstracts.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tasks")
@EqualsAndHashCode(callSuper = true)
public class Task extends BaseEntity {

	private String name;

	private String description;

	@ManyToOne
	private ProjectMenuItem item;

//	private TaskStatus status;

}
