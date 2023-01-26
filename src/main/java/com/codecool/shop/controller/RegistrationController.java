package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.JDBC.UserDaoJDBC;
import com.codecool.shop.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/registry"})
public class RegistrationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        engine.process("product/register.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDaoJDBC user_register = UserDaoJDBC.getInstance();

        boolean canRegister = false;
        try {
            canRegister = user_register.checkIfExist(username, email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (!canRegister) {
            User user = new User(username, email, password);
            try {
                user_register.add(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            req.setAttribute("The username or email is already exist!", "Invalid"); // Will be available as ${message}
        }


        resp.sendRedirect("/");


    }

}