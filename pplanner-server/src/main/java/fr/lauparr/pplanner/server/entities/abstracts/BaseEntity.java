package fr.lauparr.pplanner.server.entities.abstracts;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.lauparr.pplanner.server.entities.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
@EqualsAndHashCode
@ToString(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

	@CreatedDate
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	protected LocalDateTime createdAt;
	@LastModifiedDate
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	protected LocalDateTime updatedAt;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	protected LocalDateTime deletedAt;
	@CreatedBy
	@ManyToOne(fetch = FetchType.LAZY)
	private User createdBy;
	@LastModifiedBy
	@ManyToOne(fetch = FetchType.LAZY)
	private User updatedBy;

	@Id
	private String id;

	public static <T extends BaseEntity> Specification<T> whereActives() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.get("deletedAt"));
	}

	public static <T extends BaseEntity> Specification<T> whereInactives() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.isNotNull(root.get("deletedAt"));
	}

	@PrePersist
	public void autofill() {
		this.setId(UUID.randomUUID().toString());
	}

	public void delete() {
		this.deletedAt = LocalDateTime.now();
	}

	public boolean isActive() {
		return this.deletedAt == null;
	}
}
