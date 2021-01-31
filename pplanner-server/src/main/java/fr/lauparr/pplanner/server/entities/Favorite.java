package fr.lauparr.pplanner.server.entities;

import fr.lauparr.pplanner.server.entities.abstracts.BaseEntity;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "favorites")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Favorite extends BaseEntity {

	@ManyToOne(cascade = {CascadeType.REFRESH})
	private ProjectMenuItem menuItem;

	@Builder
	public Favorite(final ProjectMenuItem menuItem) {
		this.menuItem = menuItem;
	}
}
