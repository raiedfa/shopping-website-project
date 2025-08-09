package com.example.security.repository.mapper;

import com.example.security.model.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item();
        item.setId(rs.getInt("id"));
        item.setTitle(rs.getString("title"));
        item.setImage(rs.getString("image"));
        item.setPrice(rs.getDouble("price"));
        item.setStock(rs.getInt("stock"));
        return item;
    }
}
