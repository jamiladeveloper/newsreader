package com.jamila.rssserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.jamila.rssserver.repositories.CategoryRepository;
import com.jamila.rssserver.repositories.ConfigurationRepository;
import com.jamila.rssserver.repositories.RssFeedRepository;

@SpringBootApplication
@EnableMongoRepositories
public class RssServerApplication {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ConfigurationRepository configurationRepository;
	
	@Autowired
	RssFeedRepository rssFeedRepository;

	public static void main(String[] args) {
		SpringApplication.run(RssServerApplication.class, args);
	}

}
