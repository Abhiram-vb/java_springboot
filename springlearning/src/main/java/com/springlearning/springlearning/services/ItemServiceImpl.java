package com.springlearning.springlearning.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import com.springlearning.springlearning.entities.Items;
import com.springlearning.springlearning.mongodb.ItemsCollection;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;

@Primary
@Service
@ResponseBody

public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemsCollection mongoDb;

    @Override
    public List<Items> getItems() {

        return mongoDb.findAll();
    }

    @Override
    public ResponseEntity<Items> getItemById(String id) {
        try {
            Optional<Items> oldData0 = mongoDb.findById(id);
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

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Items> getTotalRevenueByItemAndPriceRange(String name, int quantity, int price) {
        MatchOperation matchOperation = Aggregation.match(Criteria.where("itemName").is(name)
                .andOperator(Criteria.where("quantity").gte(quantity),
                        Criteria.where("price").lte(price)));
        SortOperation sortByPopDesc = Aggregation.sort(Sort.by(Direction.DESC,
                "itemName"));
        Aggregation aggregation = Aggregation.newAggregation(matchOperation,
                sortByPopDesc);
        AggregationResults<Items> results = mongoTemplate.aggregate(aggregation,
                "items", Items.class);
        return results.getMappedResults();
    }

}
