package com.example.demo5.model.repository;

import java.sql.*;

public class Repository {
    private static final String URL = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String USER = "root";
    private static final String PASSWORD = "parola";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static void testConnection() {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT 1");

            if (resultSet.next()) {
                System.out.println("Conexiune reușită la baza de date!");
            } else {
                System.out.println("Nu s-a putut verifica conexiunea.");
            }
        } catch (SQLException e) {
            System.out.println("Eroare la conexiunea la baza de date: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Testăm conexiunea direct în repository
        testConnection();
    }
}
