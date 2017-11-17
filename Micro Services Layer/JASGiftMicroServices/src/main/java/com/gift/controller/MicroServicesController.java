package com.gift.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gift.exception.ItemNotFoundException;
import com.gift.model.Role;
import com.gift.model.User;
import com.gift.model.UserRole;
import com.gift.service.UserServiceImpl;
import com.gift.util.Response;

@RestController
@CrossOrigin
public class MicroServicesController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(MicroServicesController.class);

	/** The gift service @Autowired - Setter Injection. */
	//
	@Autowired
	private UserServiceImpl userService;

	@GetMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}

	@GetMapping("/test")
	public User test() {
		logger.info("in micro");
		return new User("ather", "gmail", "pass");
	}

	@PostMapping("/create")
	public User createUser(@Valid @RequestBody User user, HttpServletResponse response) {
		logger.info("Creating the User");
		logger.debug("Creating the User: " + user);

		Set<UserRole> userRoles = new HashSet<>();
		Role userRole = new Role();
		userRole.setName("ROLE_USER");
		userRoles.add(new UserRole(user, userRole));
		User createdUser = userService.createUser(user, userRoles);
		response.setStatus(201);
		return createdUser;
	}

	@GetMapping(value = "/user/{id}")
	public User getUserById(@Valid @PathVariable("id") int id) {
		logger.info("Retrieving the user: " + id);

		/**
		 * Optional structure to store null without having any exceptions
		 */
		User user = new User();
		try {
			user = userService.getUserById(id);

			logger.debug("Retrieving the user: " + user.getUsername());

		} catch (Exception e) {
			throw new ItemNotFoundException("User with ID " + id + " not found");

		}

		return user;
	}

	@PutMapping("/update")
	public Response updateVehicle(@Valid @RequestBody User user, HttpServletResponse response) {
		logger.info("Updating the user");

		/**
		 * Send created status if id is available
		 */
		userService.updateUser(user);
		response.setStatus(201);
		return new Response("Successfully Updated");
	}

}
