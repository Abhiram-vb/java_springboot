package com.springlearning.springlearning.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import com.springlearning.springlearning.entities.Items;
import com.springlearning.springlearning.mongodb.MongoDbConnect;

@Primary
@Service
@ResponseBody

public class ItemServiceImpl implements ItemService {
    List<Items> list;

    @Autowired
    MongoDbConnect mongoDb;

    @Override
    public List<Items> getItems() {
        return mongoDb.findAll();
    }

    @Override
    public Optional<Items> getItemById(String id) {
        System.out.println(mongoDb.findByitemName("glasses"));
        return mongoDb.findById(id);
    }

    @Override
    public Items addItem(Items item) {
        item.setItemId(UUID.randomUUID().toString().split("-")[0]);
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
