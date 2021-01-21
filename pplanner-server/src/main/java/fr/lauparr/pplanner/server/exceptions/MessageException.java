package fr.lauparr.pplanner.server.exceptions;

import lombok.Getter;

import java.util.logging.Level;

public class MessageException extends RuntimeException {

	@Getter
	private final Level level;
	@Getter
	private final String title;

	public MessageException(final Level level, final String message) {
		super(message);
		this.level = level;
		this.title = null;
	}

	public MessageException(final Level level, final String message, final Throwable cause) {
		super(message, cause);
		this.level = level;
		this.title = null;
	}

	public MessageException(final String message) {
		this(Level.SEVERE, message);
	}

	public MessageException(final String message, final Throwable cause) {
		this(Level.SEVERE, message, cause);
	}

	public MessageException(final Level level, final String message, final String title) {
		super(message);
		this.level = level;
		this.title = title;
	}

	public MessageException(final Level level, final String message, final String title, final Throwable cause) {
		super(message, cause);
		this.level = level;
		this.title = title;
	}

	public MessageException(final String message, final String title) {
		this(Level.SEVERE, message, title);
	}

	public MessageException(final String message, final String title, final Throwable cause) {
		this(Level.SEVERE, message, title, cause);
	}

}
