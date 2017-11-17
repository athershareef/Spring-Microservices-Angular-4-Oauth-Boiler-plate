package com.gift.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gift.exception.UserNotFoundException;
import com.gift.model.User;
import com.gift.util.Response;

@RestController
@CrossOrigin
public class GiftController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(GiftController.class);
	private static final String microServicesURL = "http://localhost:9090/";

	@GetMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	@GetMapping("/test")
	public User test() {
		User microUser = restTemplateBuilder.build().getForEntity(microServicesURL, User.class).getBody();
		logger.info("In normal service" + microUser.toString());
		return microUser;
	}

	@PostMapping("/login")
	public User login(@AuthenticationPrincipal User user) {
		return user;
	}

	@PostMapping("/create")
	public User createUser(@Valid @RequestBody User user, HttpServletResponse response) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		User microUser = restTemplateBuilder.build().postForEntity(microServicesURL + "create", user, User.class)
				.getBody();
		logger.info("In User Create");
		response.setStatus(201);
		return microUser;
	}

	@GetMapping("/user/{id}")
	public User getUser(@Valid @PathVariable("id") long id, HttpServletResponse response) {
		logger.info("Retrieving the user: " + id);

		/**
		 * Optional structure to store null without having any exceptions
		 */
		User microUser = new User();
		try {
			microUser = restTemplateBuilder.build().getForEntity(microServicesURL + "user/" + id, User.class).getBody();
			logger.info("In getUser " + microUser.getUsername());
			response.setStatus(200);
		} catch (Exception e) {
			throw new UserNotFoundException("User with ID " + id + " not found");

		}

		return microUser;
	}

	@PutMapping("/user/{id}")
	public Response updateUser(@Valid @PathVariable("id") long id, @Valid @RequestBody User user,
			HttpServletResponse response) {
		logger.info("Updating the user");
		logger.debug("Updating the user: " + id);

		User microUser = new User();
		try {
			ResponseEntity<String> mircoResponse = restTemplateBuilder.build()
					.getForEntity(microServicesURL + "user/" + id, String.class);

			HttpStatus status = mircoResponse.getStatusCode();

			if (status.is2xxSuccessful()) {
				user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
				logger.info("In User Update");
				restTemplateBuilder.build().put(microServicesURL + "update", user, User.class);
				response.setStatus(201);
				logger.info("In User Update : complete");
				return new Response("Update Success");
			}

			logger.info("In getUser " + microUser.getUsername());
			response.setStatus(200);
		} catch (Exception e) {
			throw new UserNotFoundException("User with ID " + id + " not found");
		}

		throw new UserNotFoundException();
	}

	@GetMapping(value = "/signout")
	public User logoutPage() {
		SecurityContextHolder.clearContext();
		return new User();
	}
}
