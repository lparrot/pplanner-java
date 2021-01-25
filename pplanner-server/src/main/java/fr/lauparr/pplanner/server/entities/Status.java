package fr.lauparr.pplanner.server.entities;


import fr.lauparr.pplanner.server.entities.abstracts.BaseEntity;
import fr.lauparr.pplanner.server.enums.StatusType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "status")
@EqualsAndHashCode(callSuper = true)
public class Status extends BaseEntity {

	private String name;

	private String color;

	@Enumerated(EnumType.STRING)
	private StatusType type;

	@ManyToOne
	private ProjectMenuItem item;

}
