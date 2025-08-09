package com.example.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.catalina.User;

public class FavoriteItem {
    private int id;
    @JsonProperty(value = "users_username")
    private String usersUserName;
    @JsonProperty(value = "item_id")
    private int itemId;

    public FavoriteItem() {
    }

    public FavoriteItem(int id, String userName, int itemId) {
        this.id = id;
        this.usersUserName = userName;
        this.itemId = itemId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return usersUserName;
    }

    public void setUserName(String userName) {
        this.usersUserName = userName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "FavoriteItem{" +
                "id=" + id +
                ", usersUserName='" + usersUserName + '\'' +
                ", itemId=" + itemId +
                '}';
    }
}
