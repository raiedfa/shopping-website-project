package com.example.security.service;

import com.example.security.model.Item;
import com.example.security.model.Order;
import com.example.security.model.OrderItems;
import com.example.security.model.Status;
import com.example.security.repository.ItemRepository;
import com.example.security.repository.OrderItemRepository;
import com.example.security.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    public Order createOrder(Order orderRequest) {
        System.out.println("📥 יצירת הזמנה חדשה...");

        Order order = new Order();
        order.setTotalPrice(orderRequest.getTotalPrice());
        order.setShippingAddress(orderRequest.getShippingAddress());
        order.setStatus(Status.TEMP);

        order = orderRepository.createOrder(order);
        System.out.println("✅ הזמנה נשמרה בהצלחה עם ID: " + order.getId());




        return order;
    }

    public Optional<Order> findById(int id) {
        System.out.println("🔍 מחפש הזמנה עם ID: " + id);
        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()) {
            System.out.println("✅ הזמנה נמצאה: " + order.get());
        } else {
            System.out.println("❌ הזמנה לא נמצאה עבור ID: " + id);
        }
        return order;
    }


    public List<Order> getOrdersByUsername(String users_username) {
        return orderRepository.findOrdersByUsername(users_username);
    }

    public void deleteById(int id) {
        orderRepository.deleteById(id);
    }

    public void recalculateTotalPrice(int orderId , double totalPrice) {
        List<OrderItems> items = orderItemRepository.getItemsInOrder(orderId);
        double total = 0;

        for (OrderItems oi : items) {
            Item item = itemRepository.findById(oi.getItemId());
            total += item.getPrice() * oi.getQuantity();
        }

        orderRepository.updateTotalPrice(orderId, total);
    }




}






