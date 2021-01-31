package fr.lauparr.pplanner.server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import fr.lauparr.pplanner.server.entities.abstracts.BaseEntity;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "members")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Member extends BaseEntity {

	private String email;

	private String lastname;

	private String firstname;

	private String job;

	@OneToMany(mappedBy = "assignee", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
	@JsonManagedReference("member_tasks")
	private List<Task> tasks = new ArrayList<>();

	@Builder
	public Member(final String email, final String lastname, final String firstname, final String job, @Singular final List<Task> tasks) {
		this.email = email;
		this.lastname = lastname;
		this.firstname = firstname;
		this.job = job;
		this.tasks = tasks;
	}

	@JsonIgnore
	public String getFullname() {
		return (this.getLastname() + ' ' + this.getFirstname()).trim();
	}
}


