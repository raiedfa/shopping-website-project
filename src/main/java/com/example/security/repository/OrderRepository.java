package com.example.security.repository;

import com.example.security.model.Order;
import com.example.security.model.Status;
import com.example.security.repository.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String ORDERS_TABLE = "orders";




    public Order createOrder(Order order) {
        String sql = "INSERT INTO " + ORDERS_TABLE + " (order_date, total_price, status, delivery_address, username) VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            order.setCreationDate(LocalDate.now());
//            ps.setTimestamp(1, Timestamp.valueOf(order.getCreationDate());
            ps.setDouble(2, order.getTotalPrice());
//            ps.setString(3, order.getStatus());
            ps.setString(4, order.getShippingAddress());
            ps.setString(5, order.getUsersUserName());
            return ps;
        }, keyHolder);

        order.setId((int) keyHolder.getKey().intValue());
        System.out.println("📌 Order saved: " + order);

        return order;
    }


    public void updateTotalPrice(int orderId, double totalPrice) {
        String sql = "UPDATE orders SET total_price = ? WHERE id = ?";
        jdbcTemplate.update(sql, totalPrice, orderId);
    }

    public void closeOrder(int orderId) {
        String sql = "UPDATE orders SET status = 'CLOSE' WHERE id = ?";
        jdbcTemplate.update(sql, orderId);
    }

    public List<Order> findOrdersByUsername(String users_username) {
        String sql = "SELECT * FROM " + ORDERS_TABLE + " WHERE users_username = ?";
        return jdbcTemplate.query(sql, new OrderMapper(), users_username);
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM " + ORDERS_TABLE + " WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    public boolean deleteUserOrdersByUsername(String username) {
        String sql = "DELETE FROM " + ORDERS_TABLE + " WHERE username = ?";
        return jdbcTemplate.update(sql, username) > 0;
    }

//    public List<Order> findByUsername(String username) {
//        String sql = "SELECT * FROM " + ORDERS_TABLE + " WHERE username = ?";
//        return jdbcTemplate.query(sql, new OrderMapper(), username);
//    }


    public Optional<Order> findById(int id) {
        String sql = "SELECT * FROM " + ORDERS_TABLE + " WHERE id = ?";
        try {
            Order order = jdbcTemplate.queryForObject(sql, new OrderMapper(), id);
            return Optional.ofNullable(order);
        } catch (Exception e) {
            return Optional.empty(); // מחזירים Optional ריק אם ההזמנה לא נמצאה
        }
    }

    public boolean existsById(Long id) {
        String sql = "SELECT COUNT(*) FROM " + ORDERS_TABLE + " WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }





    }



