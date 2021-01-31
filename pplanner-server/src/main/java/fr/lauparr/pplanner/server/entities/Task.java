package fr.lauparr.pplanner.server.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import fr.lauparr.pplanner.server.entities.abstracts.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tasks")
@EqualsAndHashCode(callSuper = true)
public class Task extends BaseEntity {

	private String name;

	private String description;

	private LocalDate dueDate;

	private LocalDate startDate;

	private LocalDate closedDate;

	@ManyToOne
	@JsonManagedReference("member_tasks")
	private Member assignee;

	@SortNatural
	@Column(precision = 14, scale = 10)
	private BigDecimal orderIndex;

	@ManyToOne
	@JsonManagedReference("menu_item_tasks")
	private ProjectMenuItem item;

	@ManyToOne
	@JsonManagedReference("menu_item_status")
	private TaskStatus status;

	@OneToMany(mappedBy = "task", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
	@JsonManagedReference("tasks_comments")
	private List<TaskComment> comments = new ArrayList<>();

	@Builder
	public Task(final String name, final String description, final ProjectMenuItem item, final TaskStatus status, final Member assignee) {
		this.name = name;
		this.description = description;
		this.item = item;
		this.status = status;
		this.assignee = assignee;
	}

	public Task addComment(final TaskComment taskComment) {
		taskComment.setTask(this);
		this.comments.add(taskComment);
		return this;
	}
}
