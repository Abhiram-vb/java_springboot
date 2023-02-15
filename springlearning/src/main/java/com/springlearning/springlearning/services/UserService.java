package com.springlearning.springlearning.services;

import java.util.List;

import com.springlearning.springlearning.entities.UserDetails;

public interface UserService {
    public UserDetails addUser(UserDetails userData);

    public List<UserDetails> getAllUsers();

    public String deleteUser(String id);
}
