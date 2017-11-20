package com.gift.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gift.exception.RegistryNotFoundException;
import com.gift.model.Registry;

@RestController
@CrossOrigin
@RequestMapping("/registry")
public class RegistryController {

	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

	private static final String microServicesURL = "http://localhost:9090/registry/";

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	@PostMapping("/create")
	public Registry createRegistry(@RequestBody Set<Long> itemIds) {
		logger.info("Creating the registry with " + itemIds.toArray());
		Registry registry = new Registry();
		try {
			registry = restTemplateBuilder.build().postForEntity(microServicesURL + "create", itemIds, Registry.class)
					.getBody();
			logger.info("In create Registry " + registry.getRegistryId());

		} catch (Exception e) {
			throw new RegistryNotFoundException("Registry creation error !");

		}

		return registry;
	}
	
	@GetMapping
	public Collection<Registry> getAllRegistries(){
		logger.info("In Retrieving all the Registries");
		Collection<Registry> registry = new HashSet<Registry>();
		try {
			registry = restTemplateBuilder.build().exchange(microServicesURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Collection<Registry>>() {
					}).getBody();
			logger.info("In get all registries ");

		} catch (Exception e) {
			throw new RegistryNotFoundException("No registries Found !");

		}

		return registry;
	}
	
	
	@PostMapping("/add")
	public Registry addItemsToRegistry(@RequestBody Set<Long> itemIds) {
		logger.info("Adding the item(s) to the registry with " + itemIds.toArray());
		Registry registry = new Registry();
		try {
			registry = restTemplateBuilder.build().postForEntity(microServicesURL + "add", itemIds, Registry.class)
					.getBody();
			logger.info("In add items to Registry " + registry.getRegistryId());

		} catch (Exception e) {
			throw new RegistryNotFoundException("Registry updation error !");

		}
		return registry;
	}
	
	@PostMapping("/delete")
	public Registry deleteItemsFromRegistry(@RequestBody Set<Long> itemIds) {
		logger.info("Deleting the item(s) from the registry with " + itemIds.toArray());
		Registry registry = new Registry();
		try {
			registry = restTemplateBuilder.build().postForEntity(microServicesURL + "delete", itemIds, Registry.class)
					.getBody();
			logger.info("In delete items from Registry " + registry.getRegistryId());

		} catch (Exception e) {
			throw new RegistryNotFoundException("Could not delete item(s) from the registry !");

		}
		return registry;
	}
	
	@GetMapping("/id/{id}")
	public Registry getRegistry(@Valid @PathVariable("id") long id, HttpServletResponse response) {
		logger.info("Retrieving the registry: " + id);
		Registry registry = new Registry();
		try {
			registry = restTemplateBuilder.build().getForEntity(microServicesURL + "id/" + id, Registry.class).getBody();
			logger.info("In getRegistry " + registry.getRegistryId());
			response.setStatus(200);
		} catch (Exception e) {
			throw new RegistryNotFoundException("Registry with ID " + id + " not found");
		}
		return registry;
	}
	
}
