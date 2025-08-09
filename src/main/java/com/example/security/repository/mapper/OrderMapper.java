package com.example.security.repository.mapper;

import com.example.security.model.Order;
import com.example.security.model.Status;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class OrderMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setUsersUserName(rs.getString("users_username"));
        order.setCreationDate(rs.getDate("creation_date").toLocalDate());
        order.setShippingAddress(rs.getString("shipping_address"));
        order.setTotalPrice(rs.getDouble("total_price"));
        order.setStatus(Status.valueOf(rs.getString("status").toUpperCase()));
        return order;
    }
}
