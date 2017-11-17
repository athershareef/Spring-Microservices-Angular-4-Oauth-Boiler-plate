package com.gift.service;

import com.gift.model.User;

public interface IUserService {

	User findByEmail(String username);

}
