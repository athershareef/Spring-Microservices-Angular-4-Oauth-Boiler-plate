package com.gift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class UserRegistrationException.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserRegistrationException extends RuntimeException {

	/** default. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new vehicle registration exception.
	 *
	 * @param message
	 *            the message
	 */
	public UserRegistrationException(String message) {
		super(message);
	}

}
