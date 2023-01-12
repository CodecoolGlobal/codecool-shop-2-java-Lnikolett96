package com.codecool.shop.dao.implementation.mem;

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
        int amount = cartContent.get(product);
        if (amount == 1) {
            cartContent.remove(product);
        } else {
            cartContent.put(product, amount - 1);
        }
    }

    @Override
    public Map<Product, Integer> getAll() {
        return cartContent;
    }
}
