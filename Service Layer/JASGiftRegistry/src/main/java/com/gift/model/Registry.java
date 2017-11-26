package com.gift.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Registry implements Serializable {
	
	private static final long serialVersionUID = 99866854751313L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "registry_id", nullable = false, updatable = false)
	private long registryId;

	@ManyToMany(mappedBy = "registry", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonIgnore
	private List<Item> registryItemList;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private User user;
	
	public Registry(long registryId, List<Item> registryItemList, User user) {
		super();
		this.registryId = registryId;
		this.registryItemList = registryItemList;
		this.user = user;
	}

	public Registry() {
		super();
	}

	public long getRegistryId() {
		return registryId;
	}

	public void setRegistryId(long registryId) {
		this.registryId = registryId;
	}

	public List<Item> getRegistryItemList() {
		return registryItemList;
	}

	public void setRegistryItemList(List<Item> registryItemList) {
		this.registryItemList = registryItemList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
