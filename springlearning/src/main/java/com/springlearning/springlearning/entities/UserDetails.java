package com.springlearning.springlearning.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userdetails")
public class UserDetails {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;

    public UserDetails(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserDetails(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserDetails() {
    }

    @Override
    public String toString() {
        return "UserDetails [name=" + name + ", email=" + email + ", password=" + password + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
