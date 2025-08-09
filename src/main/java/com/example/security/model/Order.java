package com.example.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;


public class Order {
private int id;
@JsonProperty(value = "users_username")
private String usersUserName;
@JsonProperty(value = "creation_date")
private LocalDate creationDate;
@JsonProperty(value = "shipping_address")
private String shippingAddress;
@JsonProperty(value = "total_price")
private Double totalPrice;
private Status status;

    public Order() {
    }

    public Order(int id, String usersUserName, LocalDate creationDate, String shippingAddress, Double totalPrice, Status status) {
        this.id = id;
        this.usersUserName = usersUserName;
        this.creationDate = creationDate;
        this.shippingAddress = shippingAddress;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsersUserName() {
        return usersUserName;
    }

    public void setUsersUserName(String usersUserName) {
        this.usersUserName = usersUserName;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", usersUserName='" + usersUserName + '\'' +
                ", creationDate=" + creationDate +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                '}';
    }
}
