package fr.lauparr.pplanner.server.services;

import fr.lauparr.pplanner.server.controllers.CtrlAuth;
import fr.lauparr.pplanner.server.dao.DaoUser;
import fr.lauparr.pplanner.server.dto.JwtToken;
import fr.lauparr.pplanner.server.entities.Member;
import fr.lauparr.pplanner.server.entities.User;
import fr.lauparr.pplanner.server.exceptions.MessageException;
import fr.lauparr.pplanner.server.exceptions.NotFoundException;
import fr.lauparr.pplanner.server.security.SrvToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SrvSecurity {

	@Autowired
	private DaoUser daoUser;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private SrvToken srvToken;

	public JwtToken login(final String username, final String password) {
		final User user = this.daoUser.findByEmailAndDeletedAtIsNull(username).orElse(null);

		// Si aucun utilisateur n'a été trouvé à partir du login
		if (user == null) {
			throw new MessageException("Mauvais login ou mot de passe");
		}

		// Si le mot de passe entré ne correspond pas à celui en base de données
		if (!this.passwordEncoder.matches(password, user.getPassword())) {
			throw new MessageException("Mauvais login ou mot de passe");
		}

		final String token = this.srvToken.createToken(user);

		return JwtToken.builder()
			.token(token)
			.claims(this.srvToken.getClaims(token))
			.build();
	}

	public User getUserData(final String name) {
		return this.daoUser.findByEmailAndDeletedAtIsNull(name).orElseThrow(() -> new NotFoundException("L'utilisateur n'existe pas "));
	}

	public User createAccount(final CtrlAuth.ParamsSecurityCreateAccount params) {
		final Optional<User> user = this.daoUser.findByEmailAndDeletedAtIsNull(params.getEmail());

		if (user.isPresent()) {
			throw new MessageException("Un utilisateur ayant le même email éxiste déjà");
		}

		final User userToCreate = User.builder()
			.email(params.getEmail())
			.member(Member.builder()
				.email(params.getEmail())
				.lastname(params.getLastname())
				.firstname(params.getFirstname())
				.job(params.getJob())
				.build())
			.build();

		userToCreate.setPassword(this.passwordEncoder.encode(params.getPassword()));

		this.daoUser.save(userToCreate);

		return userToCreate;
	}
}
