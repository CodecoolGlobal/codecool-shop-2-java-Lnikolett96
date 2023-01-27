package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.dao.LoginDao;
import com.codecool.shop.dao.connection.SQLDataConnection;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return res.getString(1);
            }
            return "somthing";

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int getUserId(String email, String password) {
        try {
            Connection sqlConnection = dataSource.getConnection();
            String query = "SELECT id FROM users WHERE email = ? AND password = ?";

            PreparedStatement statement = sqlConnection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return res.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int getIsAdmin(String email, String password) {
        try {
            Connection sqlConnection = dataSource.getConnection();
            String query = "SELECT isadmin FROM users WHERE email = ? AND password = ?";

            PreparedStatement statement = sqlConnection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);

            return statement.executeQuery().getInt(5);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public String getPassword(String password) {
        try {
            Connection sqlConnection = dataSource.getConnection();
            String query = "SELECT password FROM users WHERE password = ?";

            PreparedStatement statement = sqlConnection.prepareStatement(query);
            statement.setString(1, password);

            ResultSet res =  statement.executeQuery();
            if (res.next()) {
                return res.getString(1);
            }
            return "nope";

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
