package com.example.security.repository.mapper;

import com.example.security.model.FavoriteItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FavoriteItemMapper  implements RowMapper<FavoriteItem> {
    @Override
    public FavoriteItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        FavoriteItem favorite = new FavoriteItem();
        favorite.setId(rs.getInt("id"));
        favorite.setUserName(rs.getString("users_username"));
        favorite.setItemId(rs.getInt("item_id"));
        return favorite;

    }
}
