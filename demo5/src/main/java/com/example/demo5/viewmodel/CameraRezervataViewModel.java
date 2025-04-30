package com.example.demo5.viewmodel;

import com.example.demo5.model.CameraRezervata;
import com.example.demo5.model.repository.CameraRezervataRepository;
import com.example.demo5.viewmodel.commands.CameraRezervataCommands;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CameraRezervataViewModel {
    private final CameraRezervataRepository repo = new CameraRezervataRepository();

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final IntegerProperty idClient = new SimpleIntegerProperty();
    private final IntegerProperty idHotel = new SimpleIntegerProperty();
    private final IntegerProperty idCamera = new SimpleIntegerProperty();
    private final ObjectProperty<java.time.LocalDate> checkin = new SimpleObjectProperty<>();
    private final ObjectProperty<java.time.LocalDate> checkout = new SimpleObjectProperty<>();
    private final StringProperty statusRezervare = new SimpleStringProperty();
    private final StringProperty mesaj = new SimpleStringProperty();
    private final ObservableList<CameraRezervataViewModel> camereRezervate = FXCollections.observableArrayList();

    private int selectedId = -1;

    public final CameraRezervataCommands addCommand = new CameraRezervataCommands(() -> {
        try {
            CameraRezervata cameraRezervata = new CameraRezervata(0, idClient.get(), idHotel.get(), idCamera.get(), checkin.get(), checkout.get(), statusRezervare.get());
            repo.addCameraRezervata(cameraRezervata);
            mesaj.set("Cameră rezervată adăugată!");
            loadCamereRezervate();
        } catch (Exception e) {
            mesaj.set("Eroare la adăugare: " + e.getMessage());
        }
    });

    public final CameraRezervataCommands updateCommand = new CameraRezervataCommands(() -> {
        try {
            if (selectedId != -1) {
                CameraRezervata cameraRezervata = new CameraRezervata(selectedId, idClient.get(), idHotel.get(), idCamera.get(), checkin.get(), checkout.get(), statusRezervare.get());
                repo.updateCameraRezervata(cameraRezervata);
                mesaj.set("Cameră rezervată actualizată!");
                loadCamereRezervate();
            } else {
                mesaj.set("Selectează o cameră rezervată.");
            }
        } catch (Exception e) {
            mesaj.set("Eroare la actualizare: " + e.getMessage());
        }
    });

    public final CameraRezervataCommands deleteCommand = new CameraRezervataCommands(() -> {
        try {
            if (selectedId != -1) {
                repo.deleteCameraRezervata(selectedId);
                mesaj.set("Cameră rezervată ștearsă!");
                loadCamereRezervate();
            } else {
                mesaj.set("Selectează o cameră rezervată.");
            }
        } catch (Exception e) {
            mesaj.set("Eroare la ștergere: " + e.getMessage());
        }
    });

    public CameraRezervataViewModel() {
        loadCamereRezervate();
    }

    public CameraRezervataViewModel(CameraRezervata cameraRezervata) {
        this.id.set(cameraRezervata.getId());
        this.idClient.set(cameraRezervata.getIdClient());
        this.idHotel.set(cameraRezervata.getIdHotel());
        this.idCamera.set(cameraRezervata.getIdCamera());
        this.checkin.set(cameraRezervata.getCheckin());
        this.checkout.set(cameraRezervata.getCheckout());
        this.statusRezervare.set(cameraRezervata.getStatusRezervare());
        this.selectedId = cameraRezervata.getId();
    }

    private void loadCamereRezervate() {
        camereRezervate.clear();
        for (CameraRezervata cameraRezervata : repo.getAllCamereRezervate()) {
            camereRezervate.add(new CameraRezervataViewModel(cameraRezervata));
        }
    }

    public void selectCameraRezervata(CameraRezervataViewModel cameraRezervataViewModel) {
        if (cameraRezervataViewModel != null) {
            selectedId = cameraRezervataViewModel.selectedId;
            id.set(cameraRezervataViewModel.id.get());
            idClient.set(cameraRezervataViewModel.idClient.get());
            idHotel.set(cameraRezervataViewModel.idHotel.get());
            idCamera.set(cameraRezervataViewModel.idCamera.get());
            checkin.set(cameraRezervataViewModel.checkin.get());
            checkout.set(cameraRezervataViewModel.checkout.get());
            statusRezervare.set(cameraRezervataViewModel.statusRezervare.get());
        }
    }

    public IntegerProperty idProperty() { return id; }
    public IntegerProperty idClientProperty() { return idClient; }
    public IntegerProperty idHotelProperty() { return idHotel; }
    public IntegerProperty idCameraProperty() { return idCamera; }
    public ObjectProperty<java.time.LocalDate> checkinProperty() { return checkin; }
    public ObjectProperty<java.time.LocalDate> checkoutProperty() { return checkout; }
    public StringProperty statusRezervareProperty() { return statusRezervare; }
    public StringProperty mesajProperty() { return mesaj; }
    public ObservableList<CameraRezervataViewModel> getCamereRezervate() { return camereRezervate; }
}