package fr.lauparr.pplanner.server.dto;

import io.jsonwebtoken.Claims;
import lombok.Builder;
import lombok.Data;

@Data
public class JwtToken {
	private String token;
	private Claims claims;

	@Builder
	public JwtToken(final String token, final Claims claims) {
		this.token = token;
		this.claims = claims;
	}
}
