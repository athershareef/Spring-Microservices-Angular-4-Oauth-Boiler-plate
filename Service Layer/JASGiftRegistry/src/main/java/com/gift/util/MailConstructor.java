package com.gift.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.gift.model.User;

@Component
public class MailConstructor {

	public SimpleMailMessage constructNewUserEmail(User user, String password) {
		String message = "\nPassword Reset Success !!\n\nPlease use the following credentials to log in and edit your profile information"
				+ "\n\nUsername: " + user.getUsername() + "\n\nPassword: " + password;

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject("JAS Gift Registry Password reset");
		email.setText(message);
		email.setFrom("athershareefvce44@gmail.com");
		return email;
	}

}
