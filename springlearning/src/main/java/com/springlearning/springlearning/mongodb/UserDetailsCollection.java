package com.springlearning.springlearning.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springlearning.springlearning.entities.UserDetails;

public interface UserDetailsCollection extends MongoRepository<UserDetails, String> {

}
