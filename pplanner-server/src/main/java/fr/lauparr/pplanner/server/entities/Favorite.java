package fr.lauparr.pplanner.server.entities;

import fr.lauparr.pplanner.server.entities.abstracts.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "favorites")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Favorite extends BaseEntity {

	@ManyToOne(cascade = {CascadeType.REFRESH})
	private User user;

	@ManyToOne(cascade = {CascadeType.REFRESH})
	private ProjectMenuItem menuItem;

	@Builder
	public Favorite(final User user, final ProjectMenuItem menuItem) {
		this.user = user;
		this.menuItem = menuItem;
	}
}
