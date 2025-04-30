package com.example.demo5.viewmodel;

import com.example.demo5.model.Hotel;
import com.example.demo5.model.repository.HotelRepository;
import com.example.demo5.viewmodel.commands.HotelCommands;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HotelViewModel {
    private final HotelRepository repo = new HotelRepository();

    private final StringProperty nume = new SimpleStringProperty();
    private final StringProperty adresa = new SimpleStringProperty();
    private final StringProperty nrCamere = new SimpleStringProperty();
    private final StringProperty mesaj = new SimpleStringProperty();
    private final ObservableList<HotelViewModel> hoteluri = FXCollections.observableArrayList();

    private int selectedId = -1;

    public final HotelCommands addCommand = new HotelCommands(() -> {
        try {
            Hotel h = new Hotel(0, nume.get(), adresa.get(), Integer.parseInt(nrCamere.get()));
            repo.addHotel(h);
            mesaj.set("Hotel adăugat!");
            loadHotels();
        } catch (Exception e) {
            mesaj.set("Eroare la adăugare: " + e.getMessage());
        }
    });

    public final HotelCommands updateCommand = new HotelCommands(() -> {
        try {
            if (selectedId != -1) {
                Hotel h = new Hotel(selectedId, nume.get(), adresa.get(), Integer.parseInt(nrCamere.get()));
                repo.updateHotel(h);
                mesaj.set("Hotel actualizat!");
                loadHotels();
            } else {
                mesaj.set("Selectează un hotel.");
            }
        } catch (Exception e) {
            mesaj.set("Eroare la actualizare: " + e.getMessage());
        }
    });

    public final HotelCommands deleteCommand = new HotelCommands(() -> {
        try {
            if (selectedId != -1) {
                repo.deleteHotel(selectedId);
                mesaj.set("Hotel șters!");
                loadHotels();
            } else {
                mesaj.set("Selectează un hotel.");
            }
        } catch (Exception e) {
            mesaj.set("Eroare la ștergere: " + e.getMessage());
        }
    });


    public HotelViewModel() {
        loadHotels();
    }


    public HotelViewModel(Hotel hotel) {
        this.nume.set(hotel.getNume());
        this.adresa.set(hotel.getAdresa());
        this.nrCamere.set(String.valueOf(hotel.getNrCamere()));
        this.selectedId = hotel.getId();
    }

    private void loadHotels() {
        hoteluri.clear();
        for (Hotel h : repo.getAllHotels()) {
            hoteluri.add(new HotelViewModel(h));
        }
    }

    public void selectHotel(HotelViewModel hotelViewModel) {
        if (hotelViewModel != null) {
            selectedId = hotelViewModel.selectedId;
            nume.set(hotelViewModel.nume.get());
            adresa.set(hotelViewModel.adresa.get());
            nrCamere.set(hotelViewModel.nrCamere.get());
        }
    }

    public StringProperty numeProperty() { return nume; }
    public StringProperty adresaProperty() { return adresa; }
    public StringProperty nrCamereProperty() { return nrCamere; }
    public StringProperty mesajProperty() { return mesaj; }
    public ObservableList<HotelViewModel> getHoteluri() { return hoteluri; }
}