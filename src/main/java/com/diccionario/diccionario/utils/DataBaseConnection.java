package com.diccionario.diccionario.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseConnection {
    private static String url = "jdbc:mysql://localhost:3306/agilidad?serverTimezine=America/Santiago";
    private static String userName = "root";
    private static String password = "knuuto56";
    private static BasicDataSource pool;

    public static BasicDataSource getInstance() throws SQLException {
        if (pool == null){
            pool = new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(userName);
            pool.setPassword(password);
            pool.setInitialSize(100);
            pool.setMinIdle(100);
            pool.setMaxIdle(180);
            pool.setMaxTotal(180);

        }
        return pool;
    }

    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }

}
