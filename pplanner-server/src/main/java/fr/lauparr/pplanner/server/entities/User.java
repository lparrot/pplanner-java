package fr.lauparr.pplanner.server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.lauparr.pplanner.server.entities.abstracts.BaseEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity implements UserDetails {

	@NotBlank
	private String email;

	@JsonIgnore
	private String password;

	@ManyToOne
	private Group group;

	@Builder
	public User(@NotBlank final String email, final Group group) {
		this.email = email;
		this.group = group;
	}

	public Claims getClaims() {
		final Claims claims = Jwts.claims();
		claims.put("email", this.email);
		return claims;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.group.getRoles();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
