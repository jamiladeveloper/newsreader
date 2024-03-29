package com.jamila.rssserver.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("categories")
public class Category {
	
	@Id
	private String id;
	
	private String name;
	private String count;
	
	
	public Category(String id, String name, String count) {
		super();
		this.id = id;
		this.name = name;
		this.count = count;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	
	
}
