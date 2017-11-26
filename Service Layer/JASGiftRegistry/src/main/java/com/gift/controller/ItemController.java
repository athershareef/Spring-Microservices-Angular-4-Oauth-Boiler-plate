package com.gift.controller;

import java.util.Collection;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gift.exception.UserNotFoundException;
import com.gift.model.Item;

@RestController
@CrossOrigin
@RequestMapping("/item")
public class ItemController {

	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

	private static final String microServicesURL = "http://localhost:9090/item";

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	@GetMapping
	public Collection<Item> getAllItems() {
		logger.info("Retrieving the Items");
		/**
		 * Optional structure to store null without having any exceptions
		 */
		Collection<Item> items = new HashSet<Item>();
		try {
			items = restTemplateBuilder.build().exchange(microServicesURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Collection<Item>>() {
					}).getBody();
			logger.info("In get Items " + items);

		} catch (Exception e) {
			throw new UserNotFoundException("No Items Found !");

		}

		return items;
	}

}
