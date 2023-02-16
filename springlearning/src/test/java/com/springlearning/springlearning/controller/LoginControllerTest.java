package com.springlearning.springlearning.controller;

import org.junit.jupiter.api.Test;
import com.springlearning.springlearning.entities.UserDetails;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import com.springlearning.springlearning.services.UserService;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class LoginControllerTest {

    @Autowired
    UserService userService;

    @Autowired
    private TestRestTemplate restTemplate;

    static String userId;

    @Test
    void testAddUser() {
        UserDetails newUserDetails = new UserDetails();
        newUserDetails.setEmail("abhiramvaluebound@valuebound.com");
        newUserDetails.setName("Valuebound Abhiram");
        newUserDetails.setPassword("Abhiram@valuebound");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserDetails> request = new HttpEntity<UserDetails>(newUserDetails, headers);
        ResponseEntity<UserDetails> response = restTemplate.postForEntity("/signIn", request, UserDetails.class);
        userId = response.getBody().getId();
        System.out.println(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
