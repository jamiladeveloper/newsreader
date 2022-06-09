package com.jamila.rssserver.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jamila.rssserver.models.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {
	
}
