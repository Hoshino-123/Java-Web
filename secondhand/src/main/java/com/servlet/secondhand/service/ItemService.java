package com.servlet.secondhand.service;

import com.servlet.secondhand.dao.ItemDao;
import com.servlet.secondhand.entity.Item;
import java.util.List;

public class ItemService {
    private ItemDao itemDao = new ItemDao();

    public void save(Item item) {
        itemDao.save(item);
    }

    public List<Item> searchItems(String keyword) {
        return itemDao.searchByKeyword(keyword);
    }

    public List<Item> getItemsByUserId(int userId) {
        return itemDao.findByUserId(userId);
    }

    public List<Item> getAllItems() {
        return itemDao.findAll();
    }

    public boolean deleteItem(int itemId) {
        return itemDao.deleteById(itemId);
    }
}
