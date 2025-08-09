package com.example.security.service;

import com.example.security.model.FavoriteItem;
import com.example.security.model.Item;
import com.example.security.repository.FavoriteItemRepository;
import com.example.security.repository.ItemRepository;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteItemService {

    @Autowired
    private FavoriteItemRepository favoriteItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;



    public String addToFavorite(String users_username, int itemId) {
        if (!favoriteItemRepository.exists(users_username, itemId)) {
            favoriteItemRepository.addToFavorite(users_username, itemId);
            return "Item added to favorites.";
        } else {
            return "Item is already in favorites.";
        }
    }

    public String removeFavorite(String users_username, int itemId) {
        if (favoriteItemRepository.exists(users_username, itemId)) {
            favoriteItemRepository.removeFromFavorite(users_username, itemId);
            return "Item removed from favorites.";
        } else {
            return "Item was not in favorites.";
        }
    }

    public List<Item> getFavorites(String users_username) {
        return favoriteItemRepository.getFavorites(users_username);
    }
}

