package com.gift.model;

import java.util.HashSet;
import java.util.Set;

public class Items {

	private Set<Item> items = new HashSet<Item>();

	public Items(Set<Item> items) {
		super();
		this.items = items;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public Items() {
		super();
	}

}
