package com.example.security.controller;

import com.example.security.model.Item;
import com.example.security.model.Order;
import com.example.security.service.ItemService;
import com.example.security.service.OrderService;
import com.example.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/items")
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/create-item")
    public ResponseEntity<String> createItem(@RequestBody Item item) {
        if (item.getTitle() == null || item.getPrice() <= 0 || item.getStock() < 0) {
            return new ResponseEntity<>("Invalid item data", HttpStatus.BAD_REQUEST);
        }
        itemService.createItem(item);
        return ResponseEntity.ok("Item added successfully");
    }


    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestHeader(value = "Authorization", required = false) String token,
                                         @RequestBody Order order) {
        try {
            if (order == null) {
                return ResponseEntity.badRequest().body("Order cannot be null.");
            }

            if (token != null && token.startsWith("Bearer ")) {
                String jwtToken = token.substring(7);
                String username = jwtUtil.extractUsername(jwtToken);
                if (username == null || username.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token.");
                }
                order.setUsersUserName(username);
            }

            Order savedOrder = orderService.createOrder(order);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the order.");
        }
    }

    @GetMapping

    public ResponseEntity<List<Item>> findAll() {
        System.out.println("📢 מתבצעת בקשה לקבלת כל המוצרים");
        List<Item> items = itemService.findAll();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Item>> searchByTitle(@RequestParam String title) {
        List<Item> results = itemService.searchByTitle(title);
        if (results.isEmpty()) {
            return new ResponseEntity<>(results, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(results);
    }



//    @PutMapping("/items/{id}/decrease-stock")
//    public ResponseEntity<String> decreaseStock(@PathVariable int itemId, @RequestParam int quantity) {
//        try {
//            itemService.decreaseStock(itemId, quantity);
//            return ResponseEntity.ok("Stock updated successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
//        }
//    }

    @PutMapping("/items/{id}/decreaseStock")
    public ResponseEntity<?> decreaseStock(@PathVariable int itemId , @RequestParam int quantity) {
        try {
            Item item = itemService.findById(itemId);
            if (item.getStock() > 0) {
                item.setStock(item.getStock() - 1);
                itemService.createItem(item);
                return ResponseEntity.ok(item);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Out of stock");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating stock");
        }
    }




    @PostMapping("/cancel")
        public ResponseEntity<String> cancelItem(
                @RequestParam int itemId,
                @RequestParam(defaultValue = "1") int amount
        ) {
            String result = itemService.increaseItemStock(itemId, amount);
            return ResponseEntity.ok(result);
        }
    }

    







