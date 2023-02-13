package com.springlearning.springlearning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("/userDetails")
    public String userDetails() {
        return "Hello User";
    }
}
