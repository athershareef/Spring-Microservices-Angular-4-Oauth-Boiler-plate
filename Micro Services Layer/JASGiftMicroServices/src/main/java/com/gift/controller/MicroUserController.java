package com.gift.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gift.exception.ItemNotFoundException;
import com.gift.model.Role;
import com.gift.model.User;
import com.gift.model.UserRole;
import com.gift.service.UserServiceImpl;
import com.gift.util.Response;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class MicroUserController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(MicroUserController.class);

	/** The gift service @Autowired - Setter Injection. */
	//
	@Autowired
	private UserServiceImpl userService;

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

	@GetMapping("/id/{id}")
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

	@PostMapping(value = "/email")
	public User getUserByEmail(@Valid @RequestBody String email) {
		logger.info("Retrieving the user by email: " + email);

		/**
		 * Optional structure to store null without having any exceptions
		 */
		User user = new User();
		try {
			user = userService.getUserByEmail(email);

			logger.debug("Retrieving the user: " + user.getUsername());

		} catch (Exception e) {
			throw new ItemNotFoundException("User with Email ID " + email + " not found");

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
