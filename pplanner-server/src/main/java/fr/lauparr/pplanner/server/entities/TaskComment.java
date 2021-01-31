package fr.lauparr.pplanner.server.entities;

import fr.lauparr.pplanner.server.entities.abstracts.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tasks_comments")
@EqualsAndHashCode(callSuper = true)
public class TaskComment extends BaseEntity {

	@Column(columnDefinition = "TEXT")
	private String text;

	@ManyToOne
	private Member author;

	@ManyToOne
	private Task task;

	@Builder
	public TaskComment(final String text, final Member author, final Task task) {
		this.text = text;
		this.author = author;
		this.task = task;
	}
}
