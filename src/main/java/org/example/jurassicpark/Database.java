package org.example.jurassicpark;

import java.sql.*;

public class Database {

    public static Connection getConnection(String url) throws SQLException {
        return DriverManager.getConnection(url);
    }
    public static void initDatabase(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS dino (id INT, isla INT, espece INT, age INT, argent INT, temperature INT, salud INT)");
        }
    }
    public static void clearTable() {
        String sqlDelete = "DELETE FROM dino";

        try (Connection conn = Database.getConnection(Main.URL);
             PreparedStatement stmt = conn.prepareStatement(sqlDelete)) {

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Todo borrado : " + rowsAffected);

        } catch (SQLException e) {
            System.err.println("Error al borrar la tabla : " + e.getMessage());
            e.printStackTrace();
        }
    }


}