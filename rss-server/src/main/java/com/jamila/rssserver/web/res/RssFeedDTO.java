package com.jamila.rssserver.web.res;

import lombok.Getter;
import lombok.Setter;

public class RssFeedDTO {

	private String id;
	private String categoryId;
	private String categoryName;
	private String rssLink;
	
	public RssFeedDTO(String id, String categoryId, String categoryName, String rssLink) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.rssLink = rssLink;
	}

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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getRssLink() {
		return rssLink;
	}

	public void setRssLink(String rssLink) {
		this.rssLink = rssLink;
	}
	
	
	
	
}
