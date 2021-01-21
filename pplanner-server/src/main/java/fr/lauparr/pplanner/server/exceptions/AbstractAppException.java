package fr.lauparr.pplanner.server.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.lauparr.pplanner.server.dto.ApiErrorDetails;
import lombok.Getter;

@JsonIgnoreProperties("withTrace")
public abstract class AbstractAppException extends RuntimeException {

	/**
	 * Typage de l'exception
	 */
	@Getter
	protected final String type;

	/**
	 * Détail de l'erreur
	 */
	@Getter
	protected final ApiErrorDetails details = new ApiErrorDetails(this);

	/**
	 * Constructeur
	 */
	public AbstractAppException(final String message) {
		super(message);
		this.type = null;
	}

	/**
	 * Constructeur
	 */
	public AbstractAppException(final String type, final String message) {
		super(message);
		this.type = type;
	}

	/**
	 * Constructeur
	 */
	public AbstractAppException(final Throwable cause) {
		super(cause);
		this.type = null;
	}

	/**
	 * Constructeur
	 */
	public AbstractAppException(final String type, final Throwable cause) {
		super(cause);
		this.type = type;
	}

}
