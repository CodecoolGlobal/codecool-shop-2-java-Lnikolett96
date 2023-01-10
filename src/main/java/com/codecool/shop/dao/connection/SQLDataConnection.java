package com.codecool.shop.dao.connection;

import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;

public class SQLDataConnection {
    public DataSource connect(String databaseName, String user, String password) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName(databaseName);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }
}
