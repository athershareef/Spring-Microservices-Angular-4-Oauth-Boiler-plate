package com.gift.service;

import java.util.Set;

import com.gift.model.User;
import com.gift.model.UserRole;

public interface IUserService {

	User createUser(User user, Set<UserRole> userRoles);

	User getUserById(long id);

	User getUserByEmail(String email);

	User updateUser(User user);

}
