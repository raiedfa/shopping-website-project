package com.example.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderItems {
    @JsonProperty(value = "order_id")
    private int orderId;
    @JsonProperty(value = "item_id")
    private int itemId;
    private int quantity;

    public OrderItems() {
    }

    public OrderItems(int orderId, int itemId, int quantity) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "orderId=" + orderId +
                ", itemId=" + itemId +
                ", quantity=" + quantity +
                '}';
    }
}
