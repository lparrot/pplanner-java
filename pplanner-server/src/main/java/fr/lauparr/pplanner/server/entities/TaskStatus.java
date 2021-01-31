package fr.lauparr.pplanner.server.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import fr.lauparr.pplanner.server.entities.abstracts.BaseEntity;
import fr.lauparr.pplanner.server.enums.TaskStatusType;
import lombok.*;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tasks_status")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class TaskStatus extends BaseEntity {

	private String name;

	private String color;

	@SortNatural
	@Column(precision = 14, scale = 10)
	private BigDecimal orderIndex;

	@Enumerated(EnumType.STRING)
	private TaskStatusType type;

	@ManyToOne
	@JsonManagedReference("menu_item_status")
	private ProjectMenuItem item;

	@Builder
	public TaskStatus(final String name, final String color, final TaskStatusType type, final ProjectMenuItem item) {
		this.name = name;
		this.color = color;
		this.type = type;
		this.item = item;
	}
}
