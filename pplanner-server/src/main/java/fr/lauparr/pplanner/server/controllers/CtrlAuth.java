package fr.lauparr.pplanner.server.controllers;

import fr.lauparr.pplanner.server.dto.JwtToken;
import fr.lauparr.pplanner.server.projections.UserProj;
import fr.lauparr.pplanner.server.security.SrvToken;
import fr.lauparr.pplanner.server.services.SrvSecurity;
import fr.lauparr.pplanner.server.services.utils.SrvJpaUtils;
import io.jsonwebtoken.Claims;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Slf4j
@RestController
@RequestMapping(value = "/api/auth")
public class CtrlAuth {

	@Autowired
	private SrvSecurity srvSecurity;
	@Autowired
	private SrvToken srvToken;
	@Autowired
	private SrvJpaUtils srvJpaUtils;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private HttpServletRequest request;

	@PostMapping("/login")

	public JwtToken postLogin(@RequestBody final SecurityPostLoginParams params) {
		return this.srvSecurity.login(params.getUsername(), params.getPassword());
	}

	@GetMapping("/user")
	public Claims getUser(final HttpServletRequest request) {
		return this.srvToken.getClaims(request);
	}

	@GetMapping("/user_data")
	public UserProj getUserData(final Principal principal) {
		return this.srvJpaUtils.convertToDto(this.srvSecurity.getUserData(principal.getName()), UserProj.class);
	}

	@Data
	public static class SecurityPostLoginParams {
		private String username;
		private String password;
	}
}
