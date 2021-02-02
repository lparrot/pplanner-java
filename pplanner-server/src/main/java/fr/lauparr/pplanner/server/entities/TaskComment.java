package fr.lauparr.pplanner.server.entities;

import fr.lauparr.pplanner.server.entities.abstracts.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tasks_comments")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class TaskComment extends BaseEntity {

	@Column(columnDefinition = "TEXT")
	private String text;

	@ManyToOne(fetch = FetchType.LAZY)
	private Member author;

	@ManyToOne(fetch = FetchType.LAZY)
	private Task task;

	@Builder
	public TaskComment(final String text, final Member author, final Task task) {
		this.text = text;
		this.author = author;
		this.task = task;
	}
}
