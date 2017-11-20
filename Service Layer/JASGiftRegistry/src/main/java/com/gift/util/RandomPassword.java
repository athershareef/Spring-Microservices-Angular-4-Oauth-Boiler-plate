package com.gift.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomPassword {

	public String generate() {
		String caps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String small = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		String symbols = "!@#$%^&*_=+-/.?<>)";

		String characters = caps + small + numbers + symbols;

		String randomString = "";
		Random random = new Random();

		for (int i = 0; i < 10; i++) {

			randomString += characters.charAt(random.nextInt(characters.length()));

		}

		return randomString;
	};

}
