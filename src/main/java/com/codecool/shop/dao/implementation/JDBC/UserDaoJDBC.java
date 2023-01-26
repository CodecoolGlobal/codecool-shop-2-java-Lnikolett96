package com.codecool.shop.dao.implementation.JDBC;

import javax.sql.DataSource;

import com.codecool.shop.dao.connection.SQLDataConnection;
import com.codecool.shop.dao.registrationDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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

    @Override
    public boolean checkIfExist(String username, String email) throws SQLException {
        Connection sqlConnection = dataSource.getConnection();

        String query = "SELECT username, email FROM users WHERE username = ? OR email = ?;";

        PreparedStatement statement = sqlConnection.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, email);

        ResultSet queryResult = statement.executeQuery();
        List<String> resultList = queryResultToList(queryResult);
        return resultList.size() != 0;
    }

    static List<String> queryResultToList(ResultSet queryResult) {
        try {
            List<String> result = new ArrayList<>();

            while (queryResult.next()) {
                result.add(String.valueOf(queryResult.next()));
            }
            System.out.println(result);
            return result;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
