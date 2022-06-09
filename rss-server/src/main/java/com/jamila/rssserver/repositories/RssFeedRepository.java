package com.jamila.rssserver.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.jamila.rssserver.models.RssFeed;

public interface RssFeedRepository extends MongoRepository<RssFeed, String>{
	
	@Query("{categoryId: ?0}")
	List<RssFeed> getRssByCategoryId(String id);

}
