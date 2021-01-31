package fr.lauparr.pplanner.server.utils;

import fr.lauparr.pplanner.server.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public abstract class UtilsSecurity {

	public static Optional<User> getCurrentUser() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() != null && User.class.equals(authentication.getPrincipal().getClass())) {
			return Optional.of((User) authentication.getPrincipal());
		}
		return Optional.empty();
	}

}
