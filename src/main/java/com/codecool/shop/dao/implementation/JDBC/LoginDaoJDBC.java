package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.dao.LoginDao;
import com.codecool.shop.dao.connection.SQLDataConnection;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginDaoJDBC implements LoginDao {

    private static LoginDaoJDBC instance = null;
    private DataSource dataSource;

    public LoginDaoJDBC() {
        this.dataSource = new SQLDataConnection().connect();
    }

    public static LoginDaoJDBC getInstance() {
        if (instance == null) instance = new LoginDaoJDBC();
        return instance;
    }

    public String getUserName(String email, String password) {
        try {
            Connection sqlConnection = dataSource.getConnection();
            String query = "SELECT username FROM users WHERE email = ? AND password = ?";

            PreparedStatement statement = sqlConnection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);

            return statement.executeQuery().getString(1);

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
