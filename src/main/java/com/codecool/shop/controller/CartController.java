package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.JDBC.CartDaoJDBC;
import com.codecool.shop.dao.implementation.JDBC.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.mem.CartDaoMem;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.model.User;
import com.codecool.shop.service.CartService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/cart-content"})
public class CartController extends HttpServlet {

    private CartService cartService = new CartService(CartDaoJDBC.getInstance(), ProductDaoJDBC.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("products", cartService.getCartProducts());

        engine.process("product/cart.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String whichMethod = req.getParameter("method");
        int prodId = Integer.parseInt(req.getParameter("prodid"));
        String userIdNum = req.getParameter("userId");
        if (whichMethod.equals("add")) {
            try {
                cartService.productAddToCart(new User("bela","teszt@gugi.com","hehe"),prodId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                cartService.decreaseAmount(new User("bela2","teszt2@gugi.com","hehe"),prodId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}