package com.gift.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gift.exception.RegistryNotFoundException;
import com.gift.model.Item;
import com.gift.model.Registry;
import com.gift.model.User;
import com.gift.service.ItemServiceImpl;
import com.gift.service.RegistryServiceImpl;
import com.gift.service.UserServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/registry")
public class MicroRegistryController {

	private static final Logger logger = LoggerFactory.getLogger(MicroRegistryController.class);

	@Autowired
	private ItemServiceImpl itemService;

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private RegistryServiceImpl registryService;

	@PostMapping("/create")
	public Registry createRegistry(@Valid @RequestBody Set<Long> itemIds, HttpServletResponse response) {
		logger.info("Creating the Registry");
		logger.debug("With items " + itemIds);
		List<Item> items = new ArrayList<Item>();

		for (Long itemId : itemIds) {
			items.add(itemService.getItemById(itemId));
		}

		User admin = userService.getUserById(2);

		System.out.println(admin.getEmail());

		Registry registry = new Registry(1, items, admin);

		Registry createdRegistry = registryService.createRegistry(registry);

		/**
		 * Sets the Created HTTP_STATUS when Vehicle is successfully Created
		 */
		response.setStatus(201);
		return createdRegistry;
	}

	@GetMapping
	public Collection<Registry> getAllRegistries() {
		logger.info("Retrieving the Registries");
		return registryService.getAllRegistries();
	}
	
	@PostMapping("/add")
	public Registry addItemsToRegistry(@Valid @RequestBody Set<Long> itemIds, HttpServletResponse response) {
		logger.info("Adding new items to the Registry");
		logger.debug("With item(s) " + itemIds);
		List<Item> newItems = new ArrayList<Item>();
		Registry temp=registryService.getRegistryById(1);
		List<Item> oldItems= registryService.getRegistryItemList(temp);

		for (Long itemId : itemIds) {
			if(oldItems.contains(itemService.getItemById(itemId)))
			{
				continue;
			}
			else
			{
				newItems.add(itemService.getItemById(itemId));
			}
		}
		
		oldItems.addAll(newItems);

		User admin = userService.getUserById(2);

		System.out.println(admin.getEmail());

		Registry registry =new Registry(1, oldItems, admin);

		//what about the previous items in the registry?[To be Checked..]
		Registry updatedRegistry = registryService.updateRegistry(registry);

		/**
		 * Sets the Created HTTP_STATUS when Vehicle is successfully Created
		 */
		response.setStatus(201);
		return updatedRegistry;
	}
	
	@PostMapping("/delete")
	public Registry deleteItemsfromRegistry(@Valid @RequestBody Set<Long> itemIds, HttpServletResponse response) {
		logger.info("Deleting items from the Registry");
		logger.debug("With item(s) " + itemIds);
		List<Item> newItems = new ArrayList<Item>();
		Registry temp=registryService.getRegistryById(1);
		List<Item> oldItems= registryService.getRegistryItemList(temp);

		for (Long itemId : itemIds) {
			if(oldItems.contains(itemService.getItemById(itemId)))
			{
				newItems.add(itemService.getItemById(itemId));
			}
			else
			{
				continue;
			}
		}
		
		oldItems.removeAll(newItems);

		User admin = userService.getUserById(2);

		System.out.println(admin.getEmail());

		Registry registry =new Registry(1, oldItems, admin);

		//what about the previous items in the registry?[To be Checked..]
		Registry updatedRegistry = registryService.updateRegistry(registry);

		/**
		 * Sets the Created HTTP_STATUS when Vehicle is successfully Created
		 */
		response.setStatus(201);
		return updatedRegistry;
	}
	
	@GetMapping("/id/{id}")
	public Registry getRegistry(@PathVariable long id) {
		logger.info("Retrieving the Registry " + id);
		Registry registry = registryService.getRegistryById(id);

		if (registry == null) {
			throw new RegistryNotFoundException();
		}
		return registry;
	}
}
