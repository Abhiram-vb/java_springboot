package com.springlearning.springlearning.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.springlearning.springlearning.entities.Items;

public interface ItemService {
    public List<Items> getItems();

    public ResponseEntity<Items> getItemById(String id);

    public Items addItem(Items item);

    public ResponseEntity<String> updateItem(String id, Items item);

    public ResponseEntity<String> deleteItem(String id);
}
