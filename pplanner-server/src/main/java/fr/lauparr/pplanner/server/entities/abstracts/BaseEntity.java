package fr.lauparr.pplanner.server.entities.abstracts;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
@EqualsAndHashCode
public class BaseEntity implements Serializable {

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	protected LocalDateTime dateCreation;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	protected LocalDateTime dateModification;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	protected LocalDateTime dateSuppression;
	@Id
	private String id;

	public static <T extends BaseEntity> Specification<T> whereActives() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.get("dateSuppression"));
	}

	public static <T extends BaseEntity> Specification<T> whereInactives() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.isNotNull(root.get("dateSuppression"));
	}

	@PrePersist
	public void autofill() {
		this.setId(UUID.randomUUID().toString());
		this.updateDates();
	}

	@PreUpdate
	public void prePersist() {
		this.updateDates();
	}

	private void updateDates() {
		if (this.dateCreation == null) {
			this.dateCreation = LocalDateTime.now();
		}
		if (this.isUpdatableDate()) {
			this.dateModification = LocalDateTime.now();
		}
	}

	public void remove() {
		this.dateSuppression = LocalDateTime.now();
	}

	/**
	 * Méthode qui permet de savoir si on veut mettre à jour la dateModification à
	 * chaque persist ou update.
	 */
	@JsonIgnore
	public boolean isUpdatableDate() {
		return true;
	}

	public boolean isActive() {
		return this.dateSuppression == null;
	}
}
