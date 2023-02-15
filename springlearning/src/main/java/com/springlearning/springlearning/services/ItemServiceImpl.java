package com.springlearning.springlearning.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import com.springlearning.springlearning.entities.Items;
import com.springlearning.springlearning.mongodb.ItemsCollection;

@Primary
@Service
@ResponseBody

public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemsCollection mongoDb;

    @Override
    public List<Items> getItems() {
        System.out.println("came into implemenation classssssssssssssss");

        return mongoDb.findAll();
    }

    @Override
    public ResponseEntity<Items> getItemById(String id) {
        try {
            Optional<Items> oldData0 = mongoDb.findById(id);
            System.out.println(oldData0.isPresent() + "10000000000000000000000" + oldData0.get());
            if (oldData0.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(oldData0.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(oldData0.get());
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public Items addItem(Items item) {
        System.out.println(item);
        mongoDb.save(item);
        return item;
    }

    @Override
    public ResponseEntity<String> updateItem(String id, Items newItem) {
        try {
            Optional<Items> oldData0 = mongoDb.findById(id);
            if (oldData0.isPresent()) {
                Items oldData = (Items) oldData0.get();
                oldData.setItemId(id);
                oldData.setItemName(newItem.getItemName());
                oldData.setPrice(newItem.getPrice());
                oldData.setQuantity(newItem.getQuantity());
                mongoDb.save(oldData);
                return ResponseEntity.status(HttpStatus.OK).body("Data Updated Successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Data with givn Id");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> deleteItem(String id) {
        mongoDb.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Item Deleted Successfully");
    }

}
