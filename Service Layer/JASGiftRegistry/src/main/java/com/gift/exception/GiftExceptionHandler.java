package com.gift.exception;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The Class GiftExceptionHandler.
 *
 * @author ather
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@ControllerAdvice
@RestController
public class GiftExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Generic - Handle all exception.
	 *
	 * @param exception
	 *            the exception
	 * @param webRequest
	 *            the web request
	 * @return the response entity
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception exception, WebRequest webRequest) {
		GiftException giftException = new GiftException(new Date().toString(), exception.getMessage(),
				webRequest.getDescription(false));
		/**
		 * Set the return status as INTERNAL_SERVER_ERROR
		 */
		return new ResponseEntity(giftException, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Custom Handle vehicle not found exception. GiftException is used as a
	 * model to create the responses
	 *
	 * @param exception
	 *            the exception
	 * @param webRequest
	 *            the web request
	 * @return the response entity
	 */
	// @ExceptionHandler(HttpMessageNotReadableException.class)
	// public final ResponseEntity<Object>
	// handleHttpMessageNotReadableException(HttpMessageNotReadableException
	// exception,
	// WebRequest webRequest) {
	// GiftException giftException = new GiftException(new Date().toString(),
	// exception.getMessage(),
	// webRequest.getDescription(false));
	// /**
	// * Set the return status as BAD_REQUEST
	// */
	// return new ResponseEntity(giftException, HttpStatus.BAD_REQUEST);
	// }

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleVehicleNotFoundException(UserNotFoundException exception,
			WebRequest webRequest) {
		GiftException giftException = new GiftException(new Date().toString(), exception.getMessage(),
				webRequest.getDescription(false));
		/**
		 * Set the return status as NOT_FOUND
		 */
		return new ResponseEntity(giftException, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handle vehicle registration exception.
	 *
	 * @param exception
	 *            the exception
	 * @param webRequest
	 *            the web request
	 * @return the response entity
	 */
	@ExceptionHandler(UserRegistrationException.class)
	public final ResponseEntity<Object> handleVehicleRegistrationException(UserRegistrationException exception,
			WebRequest webRequest) {
		GiftException giftException = new GiftException(new Date().toString(), exception.getMessage(),
				webRequest.getDescription(false));
		/**
		 * Set the return status as BAD_REQUEST
		 */
		return new ResponseEntity(giftException, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Whenever Validation (Year/Make/Model) fails, the below customized
	 * exception is triggered.
	 *
	 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		String detailedMessage = "";

		List<FieldError> errors = exception.getBindingResult().getFieldErrors();
		/**
		 * Creating a list of validation errors for sending response
		 */
		for (FieldError error : errors) {
			detailedMessage += error.getObjectName() + " - " + error.getDefaultMessage() + " \n ";
		}
		GiftException giftException = new GiftException(new Date().toString(), "", detailedMessage);
		/**
		 * Set the return status as BAD_REQUEST
		 */
		return new ResponseEntity(giftException, HttpStatus.BAD_REQUEST);
	}
}
