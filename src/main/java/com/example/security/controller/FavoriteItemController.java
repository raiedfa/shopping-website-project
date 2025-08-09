package com.example.security.controller;

import com.example.security.model.Item;
import com.example.security.service.FavoriteItemService;
import com.example.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@CrossOrigin(origins = "http://localhost:3000")
public class FavoriteItemController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private FavoriteItemService favoriteItemService;


    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/{itemId}")
    public ResponseEntity<String> addToFavorite(@RequestHeader(value = "Authorization") String token,
                                              @PathVariable Integer itemId) {
        System.out.println("Received token: " + token);
        String jwtToken = token.substring(7);
        String users_username = jwtUtil.extractUsername(jwtToken);
        System.out.println("Extracted username: " + users_username);
        System.out.println("Received product ID: " + itemId);

        if (itemId == null) {
            return new ResponseEntity<>("Item ID is missing", HttpStatus.BAD_REQUEST);
        }

        String result = favoriteItemService.addToFavorite(users_username , itemId );
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Item>> getFavorites(@RequestHeader(value = "Authorization") String token) {
        try {
            String jwtToken = token.substring(7);
            String users_username = jwtUtil.extractUsername(jwtToken);
            List<Item> favorites = favoriteItemService.getFavorites(users_username);
            return ResponseEntity.ok(favorites);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{itemId}")
    public ResponseEntity<String> removeFavorite(@RequestHeader(value = "Authorization") String token,
                                                 @PathVariable Integer itemId) {
        try {
            String jwtToken = token.substring(7);
            String users_username = jwtUtil.extractUsername(jwtToken);
            String result = favoriteItemService.removeFavorite(users_username, itemId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error removing from favorites: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/me")
    public ResponseEntity<List<Item>> getUserFavorites(@RequestHeader(value = "Authorization") String token) {
        String jwtToken = token.substring(7);
        String users_username = jwtUtil.extractUsername(jwtToken);
        List<Item> favorites = favoriteItemService.getFavorites(users_username);
        return ResponseEntity.ok(favorites);
    }










}
