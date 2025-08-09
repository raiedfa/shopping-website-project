package com.example.security.repository.mapper;

import com.example.security.model.OrderItems;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemMapper implements RowMapper<OrderItems> {
        @Override
        public OrderItems mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrderItems orderItems = new OrderItems();
            orderItems.setOrderId(rs.getInt("order_id"));
            orderItems.setItemId(rs.getInt("item_id"));
            orderItems.setQuantity(rs.getInt("quantity"));
            return orderItems;

        }
    }


