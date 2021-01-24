package fr.lauparr.pplanner.server.security;

import fr.lauparr.pplanner.server.dao.DaoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SrvAppUserDetails implements UserDetailsService {

	@Autowired
	private DaoUser daoUser;

	@Override
	public UserDetails loadUserByUsername(final String username) {
		return this.daoUser.findByEmailAndDeletedAtIsNull(username).orElseThrow(() -> new UsernameNotFoundException("Mauvais login ou mot de passe"));
	}

}
