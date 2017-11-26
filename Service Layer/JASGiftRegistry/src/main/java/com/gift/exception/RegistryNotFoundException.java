package com.gift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RegistryNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1676L;
	
	public RegistryNotFoundException() {
		super("not found");
	}
	
	public RegistryNotFoundException(String message) {
		super(message);
	}

}
