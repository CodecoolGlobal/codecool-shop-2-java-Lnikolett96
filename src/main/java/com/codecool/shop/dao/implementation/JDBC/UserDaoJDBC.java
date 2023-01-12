package com.codecool.shop.dao.implementation.JDBC;

import javax.sql.DataSource;

import com.codecool.shop.dao.connection.SQLDataConnection;
import com.codecool.shop.dao.registrationDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoJDBC implements registrationDao{

    private static UserDaoJDBC instance = null;
    private DataSource dataSource;


    private UserDaoJDBC() {
        dataSource = new SQLDataConnection().connect();
    }

    public static UserDaoJDBC getInstance(){
        if (instance == null) instance = new UserDaoJDBC();
        return instance;
    }

    @Override
    public void add(User user) throws SQLException {
        Connection sqlConnection = dataSource.getConnection();

        String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?);";

        PreparedStatement statement = sqlConnection.prepareStatement(query);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPassword());

        statement.executeUpdate();
    }

    @Override
    public void addAdmin(User user) throws SQLException {
        Connection sqlConnection = dataSource.getConnection();

        String query = "INSERT INTO users (username, email, password, isAdmin) VALUES (?, ?, ?, ?);";

        PreparedStatement statement = sqlConnection.prepareStatement(query);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPassword());
        statement.setInt(4, 1);

        statement.executeUpdate();
    }
}
