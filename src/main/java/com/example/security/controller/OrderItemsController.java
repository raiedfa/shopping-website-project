package com.example.security.controller;

import com.example.security.model.OrderItems;
import com.example.security.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order_items")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderItemsController {

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping
    public void addOrderProduct(@RequestBody OrderItems orderItems) {
        orderItemService.addOrderItems(orderItems);
    }

    @GetMapping
    public List<OrderItems> getAllOrderItems() {
        return orderItemService.getItemsInOrder();
    }

    @GetMapping("/{order_id}")
    public List<OrderItems> getOrderItemsByOrderId(@PathVariable int orderId) {
        return orderItemService.getOrderItemsByOrderId(orderId);
    }

    @DeleteMapping
    public String deleteOrderItems(int orderId) {
        orderItemService.deleteAllItems(orderId);
        return "OrderProduct עם ID "  + " נמחק בהצלחה.";
    }


}
