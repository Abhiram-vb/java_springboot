package com.springlearning.springlearning.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Document(collection = "items")
public class Items {
    @Id
    private String itemId;
    @NotBlank(message = "Item name must be provided")
    private String itemName;
    @Min(1)
    private int quantity;
    @Min(1)
    @NotNull(message = "Price need to be given")
    private int price;

    public Items() {
    }

    public Items(String itemId, String itemName, int quantity, int price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Items [itemId=" + itemId + ", itemName=" + itemName + ", quantity=" + quantity + ", price=" + price
                + "]";
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
