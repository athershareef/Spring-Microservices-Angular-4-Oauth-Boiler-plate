package com.gift.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gift.model.Item;
import com.gift.model.Registry;
import com.gift.repository.RegistryRepository;

@Service
public class RegistryServiceImpl implements IRegistryService {

	private RegistryRepository registryRepository;

	/* (non-Javadoc)
	 * @see com.gift.service.IRegistryService#getAllRegistries()
	 */
	@Override
	public List<Registry> getAllRegistries() {
		return registryRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.gift.service.IRegistryService#getRegistryById(long)
	 */
	@Override
	public Registry getRegistryById(long id) {
		return registryRepository.findOne(id);
	}

	/* (non-Javadoc)
	 * @see com.gift.service.IRegistryService#createRegistry(com.gift.model.Registry)
	 */
	@Override
	public Registry createRegistry(Registry registry) {
		return registryRepository.save(registry);
	}

	/* (non-Javadoc)
	 * @see com.gift.service.IRegistryService#deleteRegistryById(long)
	 */
	@Override
	public void deleteRegistryById(long id) {
		registryRepository.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.gift.service.IRegistryService#updateRegistry(com.gift.model.Registry)
	 */
	@Override
	public Registry updateRegistry(Registry registry) {
		return registryRepository.save(registry);
	}
	
	@Override
	public List<Item> getRegistryItemList(Registry registry)
	{
		List<Item> registryItemList=registry.getRegistryItemList();
		return registryItemList;
	}
}
