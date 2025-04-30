package com.example.demo5.model.repository;

import com.example.demo5.model.CameraRezervata;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CameraRezervataRepository extends Repository {


    public void addCameraRezervata(CameraRezervata cameraRezervata) {
        String query = "INSERT INTO CameraRezervata (id_client, id_hotel, id_camera, checkin, checkout, statusRezervare) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cameraRezervata.getIdClient());
            statement.setInt(2, cameraRezervata.getIdHotel());
            statement.setInt(3, cameraRezervata.getIdCamera());
            statement.setDate(4, Date.valueOf(cameraRezervata.getCheckin()));  // Convert LocalDate to SQL Date
            statement.setDate(5, Date.valueOf(cameraRezervata.getCheckout())); // Convert LocalDate to SQL Date
            statement.setString(6, cameraRezervata.getStatusRezervare());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Eroare la adăugarea camerei rezervate: " + e.getMessage());
        }
    }


    public List<CameraRezervata> getAllCamereRezervate() {
        List<CameraRezervata> camereRezervate = new ArrayList<>();
        String query = "SELECT * FROM CameraRezervata";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idClient = resultSet.getInt("id_client");
                int idHotel = resultSet.getInt("id_hotel");
                int idCamera = resultSet.getInt("id_camera");
                Date checkin = resultSet.getDate("checkin");
                Date checkout = resultSet.getDate("checkout");
                String statusRezervare = resultSet.getString("statusRezervare");
                camereRezervate.add(new CameraRezervata(id, idClient, idHotel, idCamera, checkin.toLocalDate(), checkout.toLocalDate(), statusRezervare));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Eroare la obținerea camerei rezervate: " + e.getMessage());
        }
        return camereRezervate;
    }


    public void updateCameraRezervata(CameraRezervata cameraRezervata) {
        String query = "UPDATE CameraRezervata SET id_client = ?, id_hotel = ?, id_camera = ?, checkin = ?, checkout = ?, statusRezervare = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cameraRezervata.getIdClient());
            statement.setInt(2, cameraRezervata.getIdHotel());
            statement.setInt(3, cameraRezervata.getIdCamera());
            statement.setDate(4, Date.valueOf(cameraRezervata.getCheckin()));  // Convert LocalDate to SQL Date
            statement.setDate(5, Date.valueOf(cameraRezervata.getCheckout())); // Convert LocalDate to SQL Date
            statement.setString(6, cameraRezervata.getStatusRezervare());
            statement.setInt(7, cameraRezervata.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Eroare la actualizarea camerei rezervate: " + e.getMessage());
        }
    }


    public void deleteCameraRezervata(int id) {
        String query = "DELETE FROM CameraRezervata WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Eroare la ștergerea camerei rezervate: " + e.getMessage());
        }
    }


    public List<CameraRezervata> getRezervariByHotel(int idHotel) {
        List<CameraRezervata> camereRezervate = new ArrayList<>();
        String query = "SELECT * FROM CameraRezervata WHERE id_hotel = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idHotel);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idClient = resultSet.getInt("id_client");
                int idCamera = resultSet.getInt("id_camera");
                Date checkin = resultSet.getDate("checkin");
                Date checkout = resultSet.getDate("checkout");
                String statusRezervare = resultSet.getString("statusRezervare");
                camereRezervate.add(new CameraRezervata(id, idClient, idHotel, idCamera, checkin.toLocalDate(), checkout.toLocalDate(), statusRezervare));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Eroare la obținerea rezervărilor pentru hotel: " + e.getMessage());
        }
        return camereRezervate;
    }


    public List<CameraRezervata> getRezervariByData(int idHotel, String data) {
        List<CameraRezervata> camereRezervate = new ArrayList<>();
        String query = "SELECT * FROM CameraRezervata WHERE id_hotel = ? AND checkin = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idHotel);
            statement.setDate(2, Date.valueOf(data));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idClient = resultSet.getInt("id_client");
                int idCamera = resultSet.getInt("id_camera");
                Date checkin = resultSet.getDate("checkin");
                Date checkout = resultSet.getDate("checkout");
                String statusRezervare = resultSet.getString("statusRezervare");
                camereRezervate.add(new CameraRezervata(id, idClient, idHotel, idCamera, checkin.toLocalDate(), checkout.toLocalDate(), statusRezervare));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Eroare la obținerea rezervărilor după dată: " + e.getMessage());
        }
        return camereRezervate;
    }
}
