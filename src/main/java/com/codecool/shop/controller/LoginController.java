package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.JDBC.LoginDaoJDBC;
import com.codecool.shop.model.User;
import com.codecool.shop.service.LoginService;
import com.google.gson.Gson;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private LoginService loginService = new LoginService(LoginDaoJDBC.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        engine.process("product/login.html", context, resp.getWriter());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        Gson gson = new Gson();

        User user = gson.fromJson(reader, User.class);

        if (loginService.verifyPassword(user.getPassword())){
            String username = loginService.getUserName(user.getEmail(), user.getPassword());
            int userId = loginService.getUserId(user.getEmail(), user.getPassword());
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("userName", username);
            httpSession.setAttribute("userId", userId);
            resp.sendRedirect("localhost:8080/login");
        } else {
            resp.setStatus(402);
        }
    }
}