package com.gift.service;

import static com.gift.util.Messages.USER_EXISTS;

import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gift.exception.UserRegistrationException;
import com.gift.model.Role;
import com.gift.model.User;
import com.gift.model.UserRole;
import com.gift.repository.RoleRepository;
import com.gift.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	@Transactional
	public User createUser(User user, Set<UserRole> userRoles) {
		User userPresent = userRepository.findByEmail(user.getEmail());

		if (userPresent != null) {
			logger.info(USER_EXISTS);
			throw new UserRegistrationException(USER_EXISTS);
		} else {
			System.out.println("myroe" + userRoles);
			if (userRoles.size() != 0) {
				for (UserRole userRole : userRoles) {
					roleRepository.save(userRole.getRole());
				}
			} else {
				roleRepository.save(new Role());
			}

			// Combine role and user
			user.getUserRoles().addAll(userRoles);

			userPresent = userRepository.save(user);
		}

		return userPresent;
	}

	@Override
	public User getUserById(long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
