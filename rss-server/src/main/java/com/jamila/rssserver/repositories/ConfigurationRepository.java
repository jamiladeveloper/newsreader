package com.jamila.rssserver.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jamila.rssserver.models.Configuration;

public interface ConfigurationRepository extends MongoRepository<Configuration, String>{

}
