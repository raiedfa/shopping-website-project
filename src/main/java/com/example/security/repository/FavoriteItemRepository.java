package com.example.security.repository;

import com.example.security.model.FavoriteItem;
import com.example.security.model.Item;
import com.example.security.repository.mapper.FavoriteItemMapper;
import com.example.security.repository.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavoriteItemRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String FAVORITE_ITEMS_TABLE = "favorites";


    public void addToFavorite(String users_username , int itemId){
        String sql = "INSERT INTO " + FAVORITE_ITEMS_TABLE + " (users_username , itemId )  VALUES (?,?) " ;
        jdbcTemplate.update(sql , users_username, itemId);
    }




    public void removeFromFavorite(String users_username , int itemId){
        String sql = "DELETE FROM " + FAVORITE_ITEMS_TABLE +  " WHERE users_username = ? AND item_id = ?";
        jdbcTemplate.update(sql , users_username, itemId);
    }

    public List<Item> getFavorites(String users_username) {
        String sql = "SELECT i.* FROM items i JOIN " + FAVORITE_ITEMS_TABLE + " f ON i.id = f.items_id WHERE f.username = ?";
        return jdbcTemplate.query(sql, new ItemMapper(), users_username);
    }

    public FavoriteItem findByUsernameAndProduct(String users_username, int itemId) {
        String sql = "SELECT * FROM " + FAVORITE_ITEMS_TABLE + " WHERE users_username = ? AND product_id = ?";
        List<FavoriteItem> favorites = jdbcTemplate.query(sql, new FavoriteItemMapper(), users_username, itemId);
        return favorites.isEmpty() ? null : favorites.get(0);
    }


    public boolean exists(String users_username, int itemId) {
        String sql = "SELECT COUNT(*) FROM favorites WHERE users_username = ? AND item_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, users_username, itemId);
        return count != null && count > 0;
    }
}
