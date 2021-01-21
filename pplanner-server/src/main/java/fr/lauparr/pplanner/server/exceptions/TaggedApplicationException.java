package fr.lauparr.pplanner.server.exceptions;

import lombok.Getter;

public class TaggedApplicationException extends RuntimeException {

	@Getter
	private final String tag;

	public TaggedApplicationException(final String tag) {
		super();
		this.tag = this.normalizeTag(tag);
	}

	public TaggedApplicationException(final String tag, final String message) {
		super(message);
		this.tag = this.normalizeTag(tag);
	}

	public TaggedApplicationException(final String tag, final String message, final Throwable cause) {
		super(message, cause);
		this.tag = this.normalizeTag(tag);
	}

	public TaggedApplicationException(final String tag, final Throwable cause) {
		super(cause);
		this.tag = this.normalizeTag(tag);
	}

	protected TaggedApplicationException(final String tag, final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.tag = this.normalizeTag(tag);
	}

	private String normalizeTag(final String tag) {
		if (tag == null) {
			return null;
		}
		return tag.toLowerCase();
	}
}
