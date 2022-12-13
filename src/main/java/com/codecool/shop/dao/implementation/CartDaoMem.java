package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartDaoMem implements CartDao {

    private List<Product> cartContent = new ArrayList<>();
    private static CartDaoMem instance = null;

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        cartContent.add(product);
    }

    @Override
    public void remove(Product product) {
        cartContent.remove(product);
    }

    @Override
    public List<Product> getAll() {
        return cartContent;
    }
}
