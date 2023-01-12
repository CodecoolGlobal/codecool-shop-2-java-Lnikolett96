package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartService {

    CartDao cartDao;
    ProductDao productDao;

    public CartService(CartDao cartDao, ProductDao productDao) {
        this.cartDao = cartDao;
        this.productDao = productDao;
    }

    public void productAddToCart(int prodId) throws SQLException {
        cartDao.add(productDao.find(prodId));
    }

    public void decreaseAmount(int prodId) throws SQLException {
        cartDao.remove(productDao.find(prodId));
    }

    public Map<Product, Integer> getCartProducts() {
        return cartDao.getAll();
    }
}
