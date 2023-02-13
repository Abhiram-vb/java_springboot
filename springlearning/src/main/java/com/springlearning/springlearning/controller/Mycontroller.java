package com.springlearning.springlearning.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.springlearning.springlearning.entities.Items;
import com.springlearning.springlearning.services.ItemService;

import jakarta.validation.Valid;

@RestController
@ControllerAdvice
public class Mycontroller {

    @Autowired
    private ItemService itemService;

    @GetMapping(path = "/home")
    public String homePage() {
        return "Hello Abhiram Welcom Home!!";
    }

    @GetMapping(path = "/getItems")
    public List<Items> getItems() {
        return this.itemService.getItems();
    }

    @GetMapping(path = "/getItems/{itemId}")
    public Optional<Items> getItemById(@PathVariable String itemId) {
        return this.itemService.getItemById(itemId);
    }

    @PostMapping(path = "/addItem", consumes = "application/json")
    public Items postItem(@Valid @RequestBody Items item) {
        System.out.println("helooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        return this.itemService.addItem(item);
    }

    @PutMapping(path = "/updateItem/{itemId}", consumes = "application/json")
    public ResponseEntity<String> updateItem(@RequestBody Items item, @PathVariable String itemId) {
        return this.itemService.updateItem(itemId, item);
    }

    @DeleteMapping(path = "/deleteItem/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable String itemId) {
        return this.itemService.deleteItem(itemId);
    }
}
