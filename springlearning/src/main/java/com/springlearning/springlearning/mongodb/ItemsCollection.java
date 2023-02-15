package com.springlearning.springlearning.mongodb;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.springlearning.springlearning.entities.Items;

public interface ItemsCollection extends MongoRepository<Items, String> {

    List<Items> findByitemName(String string);

}
