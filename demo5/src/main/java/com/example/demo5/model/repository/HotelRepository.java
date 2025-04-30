package com.example.demo5.model.repository;

import com.example.demo5.model.Hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelRepository extends Repository {


    public void addHotel(Hotel hotel) {
        String query = "INSERT INTO Hotel (nume, adresa, nrCamere) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, hotel.getNume());
            statement.setString(2, hotel.getAdresa());
            statement.setInt(3, hotel.getNrCamere());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Hotel findById(int id) {
        String query = "SELECT * FROM Hotel WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nume = resultSet.getString("nume");
                String adresa = resultSet.getString("adresa");
                int nrCamere = resultSet.getInt("nrCamere");

                return new Hotel(id, nume, adresa, nrCamere);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Hotel> getAllHotels() {
        List<Hotel> hoteluri = new ArrayList<>();
        String query = "SELECT * FROM Hotel";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nume = resultSet.getString("nume");
                String adresa = resultSet.getString("adresa");
                int nrCamere = resultSet.getInt("nrCamere");
                hoteluri.add(new Hotel(id, nume, adresa, nrCamere));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoteluri;
    }

    // Metodă pentru actualizarea unui hotel
    public void updateHotel(Hotel hotel) {
        String query = "UPDATE Hotel SET nume = ?, adresa = ?, nrCamere = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, hotel.getNume());
            statement.setString(2, hotel.getAdresa());
            statement.setInt(3, hotel.getNrCamere());
            statement.setInt(4, hotel.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodă pentru ștergerea unui hotel
    public void deleteHotel(int id) {
        String query = "DELETE FROM Hotel WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);


            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Hotelul a fost șters cu succes.");
            } else {
                System.out.println("Nu s-a găsit niciun hotel cu acest ID.");
            }

        } catch (SQLException e) {
            System.err.println("Eroare la ștergerea hotelului: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
