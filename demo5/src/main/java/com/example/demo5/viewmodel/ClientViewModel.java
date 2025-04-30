package com.example.demo5.viewmodel;

import com.example.demo5.model.Client;
import com.example.demo5.model.repository.ClientRepository;
import com.example.demo5.viewmodel.commands.ClientCommands;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientViewModel {
    private final ClientRepository repo = new ClientRepository();

    private final StringProperty nume = new SimpleStringProperty();
    private final StringProperty telefon = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty mesaj = new SimpleStringProperty();
    private final ObservableList<ClientViewModel> clienti = FXCollections.observableArrayList();

    private int selectedId = -1;

    public final ClientCommands addCommand = new ClientCommands(() -> {
        try {
            Client client = new Client(0, nume.get(), telefon.get(), email.get());
            repo.addClient(client);
            mesaj.set("Client adăugat!");
            loadClients();
        } catch (Exception e) {
            mesaj.set("Eroare la adăugare: " + e.getMessage());
        }
    });

    public final ClientCommands updateCommand = new ClientCommands(() -> {
        try {
            if (selectedId != -1) {
                Client client = new Client(selectedId, nume.get(), telefon.get(), email.get());
                repo.updateClient(client);
                mesaj.set("Client actualizat!");
                loadClients();
            } else {
                mesaj.set("Selectează un client.");
            }
        } catch (Exception e) {
            mesaj.set("Eroare la actualizare: " + e.getMessage());
        }
    });

    public final ClientCommands deleteCommand = new ClientCommands(() -> {
        try {
            if (selectedId != -1) {
                repo.deleteClient(selectedId);
                mesaj.set("Client șters!");
                loadClients();
            } else {
                mesaj.set("Selectează un client.");
            }
        } catch (Exception e) {
            mesaj.set("Eroare la ștergere: " + e.getMessage());
        }
    });

    public ClientViewModel() {
        loadClients();
    }

    public ClientViewModel(Client client) {
        this.nume.set(client.getNume());
        this.telefon.set(client.getTelefon());
        this.email.set(client.getEmail());
        this.selectedId = client.getId();
    }

    private void loadClients() {
        clienti.clear();
        for (Client client : repo.getAllClients()) {
            clienti.add(new ClientViewModel(client));
        }
    }

    public void selectClient(ClientViewModel clientViewModel) {
        if (clientViewModel != null) {
            selectedId = clientViewModel.selectedId;
            nume.set(clientViewModel.nume.get());
            telefon.set(clientViewModel.telefon.get());
            email.set(clientViewModel.email.get());
        }
    }

    public StringProperty numeProperty() { return nume; }
    public StringProperty telefonProperty() { return telefon; }
    public StringProperty emailProperty() { return email; }
    public StringProperty mesajProperty() { return mesaj; }
    public ObservableList<ClientViewModel> getClienti() { return clienti; }
}