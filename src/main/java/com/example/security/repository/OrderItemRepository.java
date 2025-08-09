package com.example.security.repository;

import com.example.security.model.OrderItems;
import com.example.security.repository.mapper.OrderItemMapper;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderItemRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String ORDER_ITEMS_TABLE = "order_items";

    public void addOrderItems(OrderItems orderItems) {
        String sql = "INSERT INTO " + ORDER_ITEMS_TABLE + "(order_id, item_id ,quantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, orderItems.getOrderId(), orderItems.getItemId(), orderItems.getQuantity());
    }

    public void removeItemFromOrder(int orderId, int itemId) {
        String sql = "DELETE FROM " + ORDER_ITEMS_TABLE + " WHERE order_id = ? AND item_id = ?";
        jdbcTemplate.update(sql, orderId, itemId);
    }

    public List<OrderItems> getItemsInOrder(int orderId) {
        String sql = "SELECT * FROM order_items WHERE order_id = ?";
        return jdbcTemplate.query(sql, new Object[]{orderId}, new OrderItemMapper());
    }

    public void deleteAllItems(int orderId) {
        jdbcTemplate.update("DELETE FROM order_items WHERE order_id = ?" , orderId);
    }

    public List<OrderItems> getOrderItemsByOrderId(int orderId) {
        String sql = "SELECT * FROM " + ORDER_ITEMS_TABLE + " WHERE order_id = ?";
        return jdbcTemplate.query(sql, new OrderItemMapper()
                , orderId);
    }

    public void updateOrderItemQuantity(int orderId, int itemId, int quantity) {
        String sql = "UPDATE " + ORDER_ITEMS_TABLE + " SET quantity =  ? WHERE order_id = ? AND item_id = ?";
        jdbcTemplate.update(sql, quantity, orderId, itemId);


    }

    public List<OrderItems> findById(int id) {
        return getItemsInOrder(id);
    }

    public List<OrderItems> findAll() {
        String sql = "SELECT * FROM " + ORDER_ITEMS_TABLE;
        return jdbcTemplate.query(sql, new OrderItemMapper());
    }

    public void saveAll(List<OrderItems> orderItems) {
        String sql = "INSERT INTO " + ORDER_ITEMS_TABLE + " (order_id, item_id, quantity) VALUES (?, ?, ?)";

    }

//    public void decreaseStock(int itemId, int stock) {
//        String sql = "UPDATE items SET stock = stock - ? WHERE id = ? AND stock >= ?";
//        jdbcTemplate.update(sql, stock, itemId, stock);
//    }
//
//    public void increaseStock(int itemId, int stock) {
//        String sql = "UPDATE items SET stock = stock + ? WHERE id = ?";
//        jdbcTemplate.update(sql, stock, itemId);
//    }


}


