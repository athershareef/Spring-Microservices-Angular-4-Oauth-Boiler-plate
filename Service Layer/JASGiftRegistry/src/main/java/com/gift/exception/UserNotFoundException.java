package com.gift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class UserNotFoundException.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new vehicle not found exception.
	 */
	public UserNotFoundException() {
		super("not found");
	}

	/**
	 * Instantiates a new vehicle not found exception.
	 *
	 * @param message
	 *            the message
	 */
	public UserNotFoundException(String message) {
		super(message);
	}

}
