package com.example.demo5.model.repository;

import com.example.demo5.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository extends Repository {


    public void addClient(Client client) {
        String query = "INSERT INTO Client (nume, telefon, email) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client.getNume());
            statement.setString(2, client.getTelefon());
            statement.setString(3, client.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Client> getAllClients() {
        List<Client> clienti = new ArrayList<>();
        String query = "SELECT * FROM Client";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nume = resultSet.getString("nume");
                String telefon = resultSet.getString("telefon");
                String email = resultSet.getString("email");
                clienti.add(new Client(id, nume, telefon, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clienti;
    }


    public void updateClient(Client client) {
        String query = "UPDATE Client SET nume = ?, telefon = ?, email = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client.getNume());
            statement.setString(2, client.getTelefon());
            statement.setString(3, client.getEmail());
            statement.setInt(4, client.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteClient(int id) {
        String query = "DELETE FROM Client WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
