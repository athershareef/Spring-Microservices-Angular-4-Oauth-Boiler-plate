package com.gift.service;

import java.util.List;

import com.gift.model.Item;

public interface IItemService {

	List<Item> getAllItems();

	Item getItemById(long id);

	Item createItem(Item item);

	void deleteItem(long id);

}