package fr.lauparr.pplanner.server.security;

import fr.lauparr.pplanner.server.dao.DaoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SrvAppUserDetails implements UserDetailsService {

	@Autowired
	private DaoUser daoUser;

	@Override
	@Cacheable(value = "userTokenCache", key = "#username")
	public UserDetails loadUserByUsername(final String username) {
		System.out.println(username);
		return this.daoUser.findByEmailAndDeletedAtIsNull(username).orElseThrow(() -> new UsernameNotFoundException("Mauvais login ou mot de passe"));
	}

}
