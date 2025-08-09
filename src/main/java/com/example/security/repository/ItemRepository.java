package com.example.security.repository;

import com.example.security.model.Item;
import com.example.security.repository.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String ITEMS_TABLE = "items";

    public String saveItem (Item item) {
        String sql = String.format("INSERT INTO %s (title , image , price , stock) " +
                "VALUES (?,?,?,?) " , ITEMS_TABLE);
        jdbcTemplate.update(sql, item.getTitle(), item.getImage(), item.getPrice(), item.getStock());
        return "Item created successfully";
    }

    public Item findById(int itemId){
        String sql = String.format("SELECT * FROM %s WHERE id = ? ", ITEMS_TABLE);
       return jdbcTemplate.queryForObject(sql,new ItemMapper(),itemId);
    }

    public List<Item> findAll() {
        String sql = "SELECT * FROM items ";
        return jdbcTemplate.query(sql, new ItemMapper());
    }

//    public List<Item> findByName(String title) {
//        String sql = "SELECT * FROM " + ITEMS_TABLE + " WHERE title = ?";
//        try {
//            return jdbcTemplate.queryForObject(sql, new ItemMapper(), title
//            );
//        } catch (Exception e) {
//            return null;
//        }
//    }
    public List<Item> searchByTitle(String title) {
    String sql = "SELECT * FROM items WHERE LOWER(title) LIKE LOWER(?) ";
    return jdbcTemplate.query(sql, new Object[]{"%" + title + "%"}, new ItemMapper());
}




    public void decreaseStock(int itemId, int stock) {
        String sql = "UPDATE items SET stock = stock - ? WHERE id = ? AND stock >= ?";
        jdbcTemplate.update(sql, stock, itemId, stock);
    }

    public void increaseStock(int itemId, int stock) {
        String sql = "UPDATE items SET stock = stock + ? WHERE id = ?";
        jdbcTemplate.update(sql, stock, itemId);
    }

}
