package com.gift.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gift.model.Registry;
import com.gift.model.User;

/**
 * The Interface ItemRepository.
 */
@Repository("registryRepository")
public interface RegistryRepository extends JpaRepository<Registry, Long> {

	/**
	 * All methods like findAll, getByID, deleteById etc. will be present in Jpa
	 * which enables our project to be connected to Any Database possible
	 */

	List<Registry> getAllByUser(User user);

}
