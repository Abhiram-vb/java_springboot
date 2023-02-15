package com.springlearning.springlearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springlearning.springlearning.entities.UserDetails;
import com.springlearning.springlearning.services.UserService;

import jakarta.validation.Valid;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/signIn", consumes = "application/json")
    public UserDetails addUser(@Valid @RequestBody UserDetails userDetails) {
        return this.userService.addUser(userDetails);
    }

    @GetMapping("/getAllUsers")
    public List<UserDetails> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @DeleteMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return this.userService.deleteUser(userId);
    }

}
