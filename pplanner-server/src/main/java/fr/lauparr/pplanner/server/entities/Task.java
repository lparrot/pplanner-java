package fr.lauparr.pplanner.server.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import fr.lauparr.pplanner.server.entities.abstracts.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SortNatural;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tasks")
@EqualsAndHashCode(callSuper = true)
public class Task extends BaseEntity {

	private String name;

	private String description;

	@SortNatural
	@Column(precision = 14, scale = 10)
	private BigDecimal orderIndex;

	@ManyToOne
	@JsonManagedReference("menu_item_tasks")
	private ProjectMenuItem item;

	@ManyToOne
	@JsonManagedReference("menu_item_status")
	private TaskStatus status;

	@Builder
	public Task(final String name, final String description, final ProjectMenuItem item, final TaskStatus status) {
		this.name = name;
		this.description = description;
		this.item = item;
		this.status = status;
	}
}
