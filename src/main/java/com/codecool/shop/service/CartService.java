package com.codecool.shop.service;

import com.codecool.shop.dao.implementation.JDBC.CartDaoJDBC;
import com.codecool.shop.dao.implementation.JDBC.ProductDaoJDBC;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.User;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class CartService {

    CartDaoJDBC cartDaoJDBC;
    ProductDaoJDBC productDaoJDBC;

    public CartService(CartDaoJDBC cartDaoJDBC, ProductDaoJDBC productDaoJDBC) {
        this.cartDaoJDBC = cartDaoJDBC;
        this.productDaoJDBC = productDaoJDBC;
    }

    public void productAddToCart(User user, int prodId) throws SQLException {
        cartDaoJDBC.add(user,productDaoJDBC.find(prodId));
    }

    public void decreaseAmount(User user,int prodId) throws SQLException {
        cartDaoJDBC.remove(user,productDaoJDBC.find(prodId));
    }

    public Product getCartProducts() {
        return new Product("csubaka",new BigDecimal(20.32),"USD","nyenyenye",new ProductCategory(4,"Geko","","")
        ,new Supplier("fufu",""),"product_2");
    }
}
