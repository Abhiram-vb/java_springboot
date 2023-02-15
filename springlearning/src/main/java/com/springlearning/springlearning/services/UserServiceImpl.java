package com.springlearning.springlearning.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlearning.springlearning.entities.UserDetails;
import com.springlearning.springlearning.mongodb.UserDetailsCollection;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDetailsCollection mongoDb;

    @Override
    public UserDetails addUser(UserDetails userData) {
        userData.getEmail();
        mongoDb.save(userData);
        return userData;
    }

    @Override
    public List<UserDetails> getAllUsers() {
        return mongoDb.findAll();
    }

    @Override
    public String deleteUser(String id) {
        mongoDb.deleteById(id);
        return "Deleted success";
    }

}
