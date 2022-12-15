package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.PaymentResult;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.CartService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {

    private PaymentResult paymentResult;

    private CartService cartService = new CartService(CartDaoMem.getInstance(), ProductDaoMem.getInstance());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        context.setVariable("products", cartService.getCartProducts());

        engine.process("product/payment.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int zipCode = Integer.parseInt(request.getParameter("zip"));
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String payment_method = request.getParameter("payment_method");
        String credit_card_number = request.getParameter("credit_card_number");
        String email = request.getParameter("email");
        BigDecimal totalPrice = BigDecimal.ZERO;
        boolean success = true;

        paymentResult = new PaymentResult(zipCode, city, address, phone, payment_method, credit_card_number, email, totalPrice, success);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        context.setVariable("result", paymentResult);

        engine.process("product/payment_result.html", context, response.getWriter());
    }
}
