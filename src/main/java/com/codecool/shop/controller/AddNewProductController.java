package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.JDBC.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation.JDBC.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.JDBC.SupplierDaoJDBC;
import com.codecool.shop.dao.implementation.JDBC.UserDaoJDBC;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.User;
import jdk.jfr.Category;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/addNewProduct"})
public class AddNewProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        engine.process("product/addNewProduct.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String productName = req.getParameter("product_name");

        String priceString = req.getParameter("price");
        BigDecimal thisPrice = new BigDecimal(priceString);

        String currency = req.getParameter("currency");
        String description = req.getParameter("description");
        String category = req.getParameter("category");
        String supplier = req.getParameter("supplier");

        Part filepart = req.getPart("file");
        String fileName = filepart.getSubmittedFileName();
        filepart.write("/static/img/" + fileName);


        ProductCategoryDaoJDBC product_category = ProductCategoryDaoJDBC.getInstance();
        SupplierDaoJDBC this_supplier = SupplierDaoJDBC.getInstance();
        ProductDaoJDBC products = ProductDaoJDBC.getInstance();

        try {
            Product product = new Product(productName, thisPrice, currency, description, product_category.find(category), this_supplier.find(supplier), "/static/img/" + fileName);
            products.add(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}