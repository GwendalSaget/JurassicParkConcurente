package org.example.jurassicpark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    public static Connection getConnection(String url) throws SQLException {
        return DriverManager.getConnection(url);
    }
    public static void initDatabase(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS dino (id INT, isla INT, espece INT, age INT, argent INT, temperature INT, salud INT)");
            //stmt.execute("CREATE TABLE IF NOT EXISTS evento (id BIGINT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(255), profesor VARCHAR(255), lugar VARCHAR(255), fecha VARCHAR(255))");
        }
    }


}