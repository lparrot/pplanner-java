package fr.lauparr.pplanner.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

	public UnauthorizedException() {
		super();
	}

	public UnauthorizedException(final String message) {
		super(message);
	}

	public UnauthorizedException(final Throwable cause) {
		super(cause);
	}

	public UnauthorizedException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
