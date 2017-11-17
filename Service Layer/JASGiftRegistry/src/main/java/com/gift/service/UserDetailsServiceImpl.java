package com.gift.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gift.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userService.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username" + username + " not found !");
		}
		System.out.println("User: + " + user.getUsername());

		return user;
	}

}
