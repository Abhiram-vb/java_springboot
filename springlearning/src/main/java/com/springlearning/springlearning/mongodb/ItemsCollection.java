package com.springlearning.springlearning.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.springlearning.springlearning.entities.Items;

public interface ItemsCollection extends MongoRepository<Items, String> {

}
