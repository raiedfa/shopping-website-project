package com.example.security.service;

import com.example.security.model.Item;
import com.example.security.repository.ItemRepository;
import com.example.security.repository.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public String createItem(Item item) {
        if (item.getTitle() == null || item.getImage() == null || item.getPrice() == null || item.getStock() == null) {
            return "Item not created. you have to fill the item`s info .";
        }
        return itemRepository.saveItem(item);
    }


    public Item findById(int itemId) {
        return itemRepository.findById(itemId);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public List<Item> searchByTitle(String title) {
        return itemRepository.searchByTitle(title);
    }



//    public String decreaseItemStock(int itemId, int amount) {
//        Item item = itemRepository.findById(itemId);
//        if (item.getStock() < amount) {
//            return "Not enough stock.";
//        }
//        itemRepository.decreaseStock(itemId, amount);
//        return "Stock decreased.";
//    }

    public void decreaseStock(int itemId, int quantity) {
        Item item = itemRepository.findById(itemId);

        if (item.getStock() < quantity) {
            throw new RuntimeException("Not enough stock");
        }
        item.setStock(item.getStock() - quantity);
        itemRepository.saveItem(item);
    }


    public String increaseItemStock(int itemId, int amount) {
        itemRepository.increaseStock(itemId, amount);
        return "Stock increased.";
    }

}
