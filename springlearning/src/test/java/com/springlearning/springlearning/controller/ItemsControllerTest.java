package com.springlearning.springlearning.controller;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.springlearning.springlearning.entities.Items;
import com.springlearning.springlearning.services.ItemService;
import static org.assertj.core.api.Assertions.*;
import java.util.List;
import java.util.Optional;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class ItemsControllerTest {

    @Autowired
    ItemService itemService;

    @Autowired
    private TestRestTemplate restTemplate;

    static String itemId;

    @Test
    @Order(1)
    void testPostItem() {
        Items newItem = new Items();
        newItem.setItemName("Pencil");
        newItem.setPrice(10);
        newItem.setQuantity(120);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Items> request = new HttpEntity<>(newItem, headers);
        ResponseEntity<Items> response = restTemplate.postForEntity("/addItem",
                request, Items.class);
        itemId = response.getBody().getItemId();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(2)
    void testGetItemById() {
        ResponseEntity<Optional<Items>> response = restTemplate.exchange("/getItems/"
                + itemId, HttpMethod.GET, null,
                new ParameterizedTypeReference<Optional<Items>>() {
                });
        Optional<Items> items = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

    }

    @Test
    @Order(3)
    void testGetItems() {
        ResponseEntity<List<Items>> response = restTemplate.exchange("/getItems",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Items>>() {
                });
        List<Items> items = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(items.size()).isEqualTo(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(4)
    void testUpdateItem() {
        Items newItem = new Items();
        newItem.setItemName("Pen");
        newItem.setPrice(25);
        newItem.setQuantity(100);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Items> request = new HttpEntity<>(newItem, headers);
        ResponseEntity<String> response = restTemplate.exchange("/updateItem/" +
                itemId, HttpMethod.PUT, request,
                new ParameterizedTypeReference<String>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(response.getBody()).isEqualTo("Data Updated Successfully");

    }

    @Test
    @Order(5)
    void testDeleteItem() {
        ResponseEntity<String> response = restTemplate.exchange("/deleteItem/" +
                itemId, HttpMethod.DELETE, null,
                new ParameterizedTypeReference<String>() {
                });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(response.getBody()).isEqualTo("Item Deleted Successfully");

    }

    @Test
    void testPostItemWrongURL() {
        Items newItem = new Items();
        newItem.setItemName("Pencil");
        newItem.setPrice(10);
        newItem.setQuantity(120);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Items> request = new HttpEntity<>(newItem, headers);
        ResponseEntity<Items> response = restTemplate.postForEntity("/addItems",
                request, Items.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testPostItemWithNoItemName() {
        Items newItem = new Items();
        newItem.setPrice(10);
        newItem.setQuantity(120);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Items> request = new HttpEntity<>(newItem, headers);
        ResponseEntity<Items> response = restTemplate.postForEntity("/addItem",
                request, Items.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    // @Order(6)
    void testUpdateItemWithNoItemWithId() {
        Items newItem = new Items();
        newItem.setItemName("Pen");
        newItem.setPrice(25);
        newItem.setQuantity(100);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Items> request = new HttpEntity<>(newItem, headers);
        ResponseEntity<String> response = restTemplate.exchange("/updateItem/" +
                itemId, HttpMethod.PUT, request,
                new ParameterizedTypeReference<String>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertThat(response.getBody()).isEqualTo("No Data with givn Id");
    }

    @Test
    // @Order(6)
    void testGetItemWithWrongId() {
        ResponseEntity<Optional<Items>> response = restTemplate.exchange("/getItems/"
                + itemId, HttpMethod.GET, null,
                new ParameterizedTypeReference<Optional<Items>>() {
                });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

}
