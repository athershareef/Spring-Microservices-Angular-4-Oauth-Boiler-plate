package com.gift.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gift.model.Item;
import com.gift.repository.ItemRepository;

@Service
public class ItemServiceImpl implements IItemService {

	// private static final Logger logger =
	// LoggerFactory.getLogger(ItemServiceImpl.class);

	@Autowired
	private ItemRepository itemRepository;

	/* (non-Javadoc)
	 * @see com.gift.service.IItemService#getAllItems()
	 */
	@Override
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.gift.service.IItemService#getItemById(long)
	 */
	@Override
	public Item getItemById(long id) {
		return itemRepository.findOne(id);
	}

	/* (non-Javadoc)
	 * @see com.gift.service.IItemService#createItem(com.gift.model.Item)
	 */
	@Override
	public Item createItem(Item item) {
		return itemRepository.save(item);
	}

	/* (non-Javadoc)
	 * @see com.gift.service.IItemService#deleteItem(long)
	 */
	@Override
	public void deleteItem(long id) {
		itemRepository.delete(id);
	}

}
