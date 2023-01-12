package com.codecool.shop.dao.connection;

import javax.sql.DataSource;

import org.postgresql.PGEnvironment;
import org.postgresql.ds.PGSimpleDataSource;

public class SQLDataConnection {
    // "MonsterWebshop", "mimi", "vmimif"
    public DataSource connect() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String database = System.getenv("databaseName");
        String user = System.getenv("user");
        String password = System.getenv("password");
        dataSource.setDatabaseName(database);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }
}
