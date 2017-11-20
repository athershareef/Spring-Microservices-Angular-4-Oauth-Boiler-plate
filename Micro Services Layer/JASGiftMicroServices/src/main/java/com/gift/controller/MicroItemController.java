package com.gift.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gift.exception.ItemNotFoundException;
import com.gift.exception.UserRegistrationException;
import com.gift.model.Item;
import com.gift.service.ItemServiceImpl;
import com.gift.util.Response;

@RestController
@CrossOrigin
@RequestMapping("/item")
public class MicroItemController {

	private static final Logger logger = LoggerFactory.getLogger(MicroItemController.class);

	@Autowired
	private ItemServiceImpl itemService;

	@PostMapping("/create")
	public Item createItem(@Valid @RequestBody Item item, HttpServletResponse response) {
		logger.info("Creating the Item");
		logger.debug("Creating the Item: " + item);
		Item oldItem = itemService.getItemById(item.getItemId());

		if (oldItem != null) {
			throw new UserRegistrationException("Item is already present !");
		}

		Item createdItem = itemService.createItem(item);

		/**
		 * Sets the Created HTTP_STATUS when Vehicle is successfully Created
		 */
		response.setStatus(201);
		return createdItem;
	}

	@GetMapping
	public Collection<Item> getAllItems() {
		logger.info("Retrieving the Items");
		return itemService.getAllItems();
	}

	@GetMapping("/id/{id}")
	public Item getItemById(@PathVariable long id) {
		logger.info("Retrieving the Item " + id);
		Item item = itemService.getItemById(id);

		if (item == null) {
			throw new ItemNotFoundException();
		}
		return item;
	}

	@DeleteMapping(value = "/id/{id}")
	public Response deleteItem(@Valid @PathVariable("id") long id) {
		logger.info("Deleting the Item: " + id);

		Item itemtoDelete = itemService.getItemById(id);
		itemService.deleteItem(id);
		/**
		 * idForDelete used to detect the delete success
		 */
		if (itemtoDelete != null)
			return new Response("Deleted Item with id " + id);

		/**
		 * Else create a new Response message and send via HTTP
		 */

		return new Response("Unable to delete");
	}

}
