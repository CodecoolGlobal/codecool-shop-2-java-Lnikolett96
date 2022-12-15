package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import java.util.HashMap;
import java.util.Map;

public class CartDaoMem implements CartDao {

    private Map<Product, Integer> cartContent = new HashMap<>();
    private static CartDaoMem instance = null;

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        cartContent.merge(product, 1, Integer::sum);
    }

    @Override
    public void remove(Product product) {
        cartContent.forEach((key, value) ->{
            if (product.getName().equals(key.getName()) && value != 0) {
                cartContent.merge(key, 1, Integer::sum);
            } else {
                cartContent.remove(product);
            }
        });
    }

    @Override
    public Map<Product, Integer> getAll() {
        return cartContent;
    }
}
