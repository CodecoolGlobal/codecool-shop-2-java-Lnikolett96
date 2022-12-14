package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;

import java.util.List;

public class CartService {

    CartDao cartDao;
    ProductDao productDao;

    public CartService(CartDao cartDao, ProductDao productDao) {
        this.cartDao = cartDao;
        this.productDao = productDao;
    }

    public void productAddToCart(int prodId) {
        cartDao.add(productDao.find(prodId));
    }






    public List<Product> getCartProducts() {
        return cartDao.getAll();
    }

}
