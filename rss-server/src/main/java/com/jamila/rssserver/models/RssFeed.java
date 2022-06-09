package com.jamila.rssserver.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("rssfeeds")
public class RssFeed {
	
	@Id
	private String id;
	
	private String categoryId;
	private String rssLink;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getRssLink() {
		return rssLink;
	}
	public void setRssLink(String rssLink) {
		this.rssLink = rssLink;
	}
	
	
}
