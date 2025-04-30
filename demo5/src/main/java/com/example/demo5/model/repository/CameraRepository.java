package com.example.demo5.model.repository;

import com.example.demo5.model.Camera;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CameraRepository extends Repository {

    public void addCamera(Camera camera) {
        String query = "INSERT INTO camera (id_hotel, numarCamera, pret, facilitati) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, camera.getIdHotel());
            statement.setString(2, camera.getNumarCamera());
            statement.setDouble(3, camera.getPret());
            statement.setString(4, camera.getFacilitati());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public List<Camera> getAvailableCameras(int idHotel, LocalDate checkIn, LocalDate checkOut) {
        List<Camera> availableCameras = new ArrayList<>();
        String query = "SELECT * FROM camera WHERE id_hotel = ? AND id NOT IN (" +
                "SELECT id_camera FROM CameraRezervata WHERE " +
                "(? < checkOut AND ? > checkIn))";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idHotel);
            statement.setDate(2, Date.valueOf(checkIn));
            statement.setDate(3, Date.valueOf(checkOut));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    availableCameras.add(new Camera(
                            resultSet.getInt("id"),
                            resultSet.getInt("id_hotel"),
                            resultSet.getString("numarCamera"),
                            resultSet.getDouble("pret"),
                            resultSet.getString("facilitati")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableCameras;
    }

    public void saveAvailableCamerasToCsv(int idHotel, LocalDate checkIn, LocalDate checkOut, String filePath) {
        List<Camera> availableCameras = getAvailableCameras(idHotel, checkIn, checkOut);
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("ID,ID_Hotel,Numar_Camera,Pret,Facilitati");
            for (Camera camera : availableCameras) {
                writer.printf("%d,%d,%s,%.2f,%s%n",
                        camera.getId(),
                        camera.getIdHotel(),
                        camera.getNumarCamera(),
                        camera.getPret(),
                        camera.getFacilitati());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Camera> getCamerasByHotelId(int idHotel) {
        List<Camera> cameras = new ArrayList<>();
        String query = "SELECT * FROM camera WHERE id_hotel = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idHotel);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cameras.add(new Camera(
                            resultSet.getInt("id"),
                            resultSet.getInt("id_hotel"),
                            resultSet.getString("numarCamera"),
                            resultSet.getDouble("pret"),
                            resultSet.getString("facilitati")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cameras;
    }
    public void saveAvailableCamerasToDoc(int idHotel, LocalDate checkIn, LocalDate checkOut, String filePath) {
        List<Camera> availableCameras = getAvailableCameras(idHotel, checkIn, checkOut);
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("Available Rooms Report");
            writer.println("======================");
            writer.printf("Hotel ID: %d%n", idHotel);
            writer.printf("Check-In: %s%n", checkIn);
            writer.printf("Check-Out: %s%n", checkOut);
            writer.println();
            writer.println("ID | Numar Camera | Pret | Facilitati");
            writer.println("-------------------------------------");
            for (Camera camera : availableCameras) {
                writer.printf("%d | %s | %.2f | %s%n",
                        camera.getId(),
                        camera.getNumarCamera(),
                        camera.getPret(),
                        camera.getFacilitati());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateCamera(Camera camera) {
        String query = "UPDATE camera SET id_hotel = ?, numarCamera = ?, pret = ?, facilitati = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, camera.getIdHotel());
            statement.setString(2, camera.getNumarCamera());
            statement.setDouble(3, camera.getPret());
            statement.setString(4, camera.getFacilitati());
            statement.setInt(5, camera.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCamera(int id) {
        String query = "DELETE FROM camera WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Camera> getAllCameras() {
        List<Camera> cameras = new ArrayList<>();
        String query = "SELECT * FROM camera";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                cameras.add(new Camera(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_hotel"),
                        resultSet.getString("numarCamera"),
                        resultSet.getDouble("pret"),
                        resultSet.getString("facilitati")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cameras;
    }
}