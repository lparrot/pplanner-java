package fr.lauparr.pplanner.server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.lauparr.pplanner.server.entities.abstracts.BaseEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class User extends BaseEntity implements UserDetails {

	@NotBlank
	private String email;

	@JsonIgnore
	private String password;

	@ManyToOne(fetch = FetchType.LAZY)
	private Group group;

	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Member member;

	@Builder
	public User(@NotBlank final String email, final Group group, final Member member) {
		this.email = email;
		this.group = group;
		this.member = member;
	}

	public Claims getClaims() {
		final Claims claims = Jwts.claims();
		claims.put("id", this.getId());
		return claims;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.group == null) {
			return AuthorityUtils.NO_AUTHORITIES;
		}
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
