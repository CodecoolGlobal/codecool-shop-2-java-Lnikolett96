package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartDaoMem implements CartDao {

    private List<Product> inTheCart = new ArrayList<>();
    private static CartDaoMem instance = null;

    private static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        inTheCart.add(product);
    }

    @Override
    public void remove(Product product) {
        inTheCart.remove(product);
    }

    @Override
    public List<Product> getAll() {
        return inTheCart;
    }
}
