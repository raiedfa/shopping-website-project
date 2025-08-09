package com.example.security.service;

import com.example.security.model.Item;
import com.example.security.model.OrderItems;
import com.example.security.repository.ItemRepository;
import com.example.security.repository.OrderItemRepository;
import com.example.security.repository.mapper.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItems> getItemsInOrder() {
        return orderItemRepository.findAll();
    }

    public void addOrderItems(OrderItems orderItems) {
        orderItemRepository.addOrderItems( orderItems);

    }

    public void removeItemFromOrder(int orderId, int itemId) {
       orderItemRepository.removeItemFromOrder(orderId , itemId);
    }

//    public OrderProduct updateOrderProduct(Long id, OrderProduct updatedOrderProduct) {
//        return orderProductRepository.findById(id).map(orderProduct -> {
//            orderProduct.setOrder(updatedOrderProduct.getOrder());
//            orderProduct.setProduct(updatedOrderProduct.getProduct());
//            orderProduct.setQuantity(updatedOrderProduct.getQuantity());
//            return orderProductRepository.save(orderProduct);
//        }).orElseThrow(() -> new RuntimeException("OrderProduct with ID " + id + " not found"));
//    }

    public List<OrderItems> getOrderItemsByOrderId(int orderId) {
        return orderItemRepository.getOrderItemsByOrderId(orderId);
    }

    public void deleteAllItems(int orderId) {
       orderItemRepository.deleteAllItems(orderId);
    }























}
