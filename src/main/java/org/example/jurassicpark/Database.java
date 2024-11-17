package org.example.jurassicpark;

import java.sql.*;

public class Database {

    private static Connection connection;

    public static void suppressSQLiteErrors(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA busy_timeout = 1000"); // Configure un délai d'attente
            stmt.execute("PRAGMA synchronous = OFF");   // Désactive certaines vérifications
        }
    }

    public static Connection getConnection(String url) throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url);

            try (Statement stmt = connection.createStatement()) {
                stmt.execute("PRAGMA journal_mode=WAL;");
            }
        }
        suppressSQLiteErrors(connection);
        return connection;
    }

    public static void initDatabase(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS dino (id INT, isla INT, espece INT, age INT, argent INT, temperature INT, salud INT)");
        }
    }

    public static void clearTable() {
        String sqlDelete = "DELETE FROM dino";

        try (PreparedStatement stmt = getConnection(Main.URL).prepareStatement(sqlDelete)) {
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Todo borrado : " + rowsAffected);
        } catch (SQLException e) {
            System.err.println("Error al borrar la tabla : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
