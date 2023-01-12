package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.mem.CartDaoMem;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.model.PaymentResult;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.CartService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {

    private PaymentResult paymentResult;

    private CartService cartService = new CartService(CartDaoMem.getInstance(), ProductDaoMem.getInstance());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        context.setVariable("products", cartService.getCartProducts());

        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Product product: cartService.getCartProducts().keySet()) {
            totalPrice = totalPrice.add(product.getDefaultPrice().multiply(BigDecimal.valueOf(cartService.getCartProducts().get(product))));
        }

        context.setVariable("totalPrice", totalPrice);

        engine.process("product/payment.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PaymentResult paymentResult = getPaymentResult(request);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        context.setVariable("result", paymentResult);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String jsonString = gson.toJson(paymentResult);

        FileWriter file = new FileWriter(paymentResult.getId().toString() + " " + LocalDate.now().toString() + ".txt");
        file.write(jsonString);
        file.close();

        engine.process("product/payment_result.html", context, response.getWriter());
    }

    private PaymentResult getPaymentResult(HttpServletRequest request) {
        UUID id = UUID.randomUUID();
        String name = request.getParameter("name");
        int zipCode = Integer.parseInt(request.getParameter("zip"));
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String payment_method = request.getParameter("payment_method");
        String credit_card_number = request.getParameter("credit_card_number");
        String credit_card_expiry = request.getParameter("credit_card_expiry");
        String credit_card_cvv = request.getParameter("credit_card_cvv");
        String email = request.getParameter("email");
        boolean success = true;

        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Product product: cartService.getCartProducts().keySet()) {
            totalPrice = totalPrice.add(product.getDefaultPrice().multiply(BigDecimal.valueOf(cartService.getCartProducts().get(product))));
        }

        return new PaymentResult(id, name, zipCode, city, address, phone, payment_method, credit_card_number, credit_card_expiry, credit_card_cvv, email, totalPrice, success);
    }
}
