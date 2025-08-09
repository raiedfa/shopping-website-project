package com.example.security.controller;

import com.example.security.model.Item;
import com.example.security.model.Order;
import com.example.security.model.OrderItems;
import com.example.security.model.Status;
import com.example.security.service.ItemService;
import com.example.security.service.OrderService;
import com.example.security.service.UserService;
import com.example.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<?> createOrder(@RequestBody Order order,
                                         @RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("❌ Token is missing or invalid!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("❌ Token is missing!");
        }

        String token = authHeader.replace("Bearer ", "");
        String users_username = jwtUtil.extractUsername(token);

        System.out.println(" User: " + users_username);
        System.out.println(" Order : " + order);

        if (order == null || order.getShippingAddress() == null) {
            return ResponseEntity.badRequest().body("❌ ההזמנה אינה תקינה.");
        }

        Order order1 = new Order();
        order.setId(order1.getId());
        order.setTotalPrice(order1.getTotalPrice());
        order.setShippingAddress(order1.getShippingAddress());
        order.setStatus(Status.TEMP);
        order.setUsersUserName(users_username);


        return null;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<?> getUserOrders(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("❌ Token is missing!");
        }

        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        List<Order> orders = orderService.getOrdersByUsername(username);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        System.out.println("🔍 מחפש הזמנה עם ID: " + id);
        Optional<Order> order = orderService.findById(id);

        return order.map(ResponseEntity::ok)
                .orElseGet(() -> {
                    System.out.println("❌ הזמנה לא נמצאה עבור ID: " + id);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                });
    }

    @GetMapping("/user-orders/{username}")
    public ResponseEntity<List<Order>> getOrdersByUserName(@RequestParam String users_username) {
        List<Order> orders = orderService.getOrdersByUsername(users_username);
        return ResponseEntity.ok(orders);
    }




}


