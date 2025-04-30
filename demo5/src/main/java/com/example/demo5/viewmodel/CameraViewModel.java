package com.example.demo5.viewmodel;

import com.example.demo5.model.Camera;
import com.example.demo5.model.repository.CameraRepository;
import com.example.demo5.viewmodel.commands.CameraCommands;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List; // Add this import

public class CameraViewModel {
    private final CameraRepository repo = new CameraRepository();

    private final IntegerProperty idHotel = new SimpleIntegerProperty();
    private final StringProperty numarCamera = new SimpleStringProperty();
    private final DoubleProperty pret = new SimpleDoubleProperty();
    private final StringProperty facilitati = new SimpleStringProperty();
    private final StringProperty mesaj = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> checkIn = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> checkOut = new SimpleObjectProperty<>();
    private final ObservableList<CameraViewModel> camere = FXCollections.observableArrayList();

    private int selectedId = -1;

    public final CameraCommands showAvailableCommand = new CameraCommands(() -> {
        try {
            int idHotelValue = idHotel.get(); // Get the value from the ViewModel
            List<Camera> availableCameras = repo.getAvailableCameras(idHotelValue, checkIn.get(), checkOut.get());
            camere.clear();
            for (Camera c : availableCameras) {
                camere.add(new CameraViewModel(c));
            }
            mesaj.set("Available rooms displayed.");
        } catch (Exception e) {
            mesaj.set("Error displaying available rooms: " + e.getMessage());
        }
    });

    public final CameraCommands saveCsvCommand = new CameraCommands(() -> {
        try {
            int idHotelValue = idHotel.get();
            LocalDate checkInDate = checkIn.get();
            LocalDate checkOutDate = checkOut.get();
            String filePath = "available_rooms.csv";
            repo.saveAvailableCamerasToCsv(idHotelValue, checkInDate, checkOutDate, filePath);
            mesaj.set("Available rooms saved to CSV: " + filePath);
        } catch (Exception e) {
            mesaj.set("Error saving to CSV: " + e.getMessage());
        }
    });

    public final CameraCommands saveDocCommand = new CameraCommands(() -> {
        try {
            int idHotelValue = idHotel.get();
            LocalDate checkInDate = checkIn.get();
            LocalDate checkOutDate = checkOut.get();
            String filePath = "available_rooms.doc";
            repo.saveAvailableCamerasToDoc(idHotelValue, checkInDate, checkOutDate, filePath);
            mesaj.set("Available rooms saved to DOC: " + filePath);
        } catch (Exception e) {
            mesaj.set("Error saving to DOC: " + e.getMessage());
        }
    });

    public final CameraCommands addCommand = new CameraCommands(() -> {
        try {
            if (numarCamera.get() == null || numarCamera.get().isEmpty()) {
                mesaj.set("Numărul camerei nu poate fi gol!");
                return;
            }
            Camera camera = new Camera(0, idHotel.get(), numarCamera.get(), pret.get(), facilitati.get());
            repo.addCamera(camera);
            mesaj.set("Cameră adăugată cu succes!");
            loadCameras();

            // Clear input fields
            idHotel.set(0);
            numarCamera.set("");
            pret.set(0.0);
            facilitati.set("");
        } catch (Exception e) {
            mesaj.set("Eroare la adăugare: " + e.getMessage());
        }
    });
    public final CameraCommands showHotelRoomsCommand = new CameraCommands(() -> {
        try {
            int idHotelValue = idHotel.get(); // Get the value from the ViewModel
            List<Camera> hotelCameras = repo.getCamerasByHotelId(idHotelValue);
            camere.clear();
            for (Camera c : hotelCameras) {
                camere.add(new CameraViewModel(c));
            }
            mesaj.set("Rooms for hotel ID " + idHotelValue + " displayed.");
        } catch (Exception e) {
            mesaj.set("Error displaying hotel rooms: " + e.getMessage());
        }
    });
    public final CameraCommands updateCommand = new CameraCommands(() -> {
        try {
            if (selectedId != -1) {
                Camera camera = new Camera(selectedId, idHotel.get(), numarCamera.get(), pret.get(), facilitati.get());
                repo.updateCamera(camera);
                mesaj.set("Cameră actualizată cu succes!");
                loadCameras();
            } else {
                mesaj.set("Selectează o cameră pentru actualizare.");
            }
        } catch (Exception e) {
            mesaj.set("Eroare la actualizare: " + e.getMessage());
        }
    });

    public final CameraCommands deleteCommand = new CameraCommands(() -> {
        try {
            if (selectedId != -1) {
                repo.deleteCamera(selectedId);
                mesaj.set("Cameră ștearsă cu succes!");
                loadCameras();
            } else {
                mesaj.set("Selectează o cameră pentru ștergere.");
            }
        } catch (Exception e) {
            mesaj.set("Eroare la ștergere: " + e.getMessage());
        }
    });

    public CameraViewModel() {
        loadCameras();
    }

    public CameraViewModel(Camera camera) {
        this.idHotel.set(camera.getIdHotel());
        this.numarCamera.set(camera.getNumarCamera());
        this.pret.set(camera.getPret());
        this.facilitati.set(camera.getFacilitati());
        this.selectedId = camera.getId();
    }

    private void loadCameras() {
        camere.clear();
        for (Camera c : repo.getAllCameras()) {
            camere.add(new CameraViewModel(c));
        }
    }

    public void selectCamera(CameraViewModel cameraViewModel) {
        if (cameraViewModel != null) {
            selectedId = cameraViewModel.selectedId;
            idHotel.set(cameraViewModel.idHotel.get());
            numarCamera.set(cameraViewModel.numarCamera.get());
            pret.set(cameraViewModel.pret.get());
            facilitati.set(cameraViewModel.facilitati.get());
        } else {

            selectedId = -1;
            idHotel.set(0);
            numarCamera.set("");
            pret.set(0.0);
            facilitati.set("");
        }
    }

    public IntegerProperty idHotelProperty() { return idHotel; }
    public StringProperty numarCameraProperty() { return numarCamera; }
    public DoubleProperty pretProperty() { return pret; }
    public StringProperty facilitatiProperty() { return facilitati; }
    public StringProperty mesajProperty() { return mesaj; }
    public ObjectProperty<LocalDate> checkInProperty() { return checkIn; }
    public ObjectProperty<LocalDate> checkOutProperty() { return checkOut; }
    public ObservableList<CameraViewModel> getCamere() { return camere; }
}