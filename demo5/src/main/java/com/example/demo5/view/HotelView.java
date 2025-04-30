package com.example.demo5.view;

import com.example.demo5.viewmodel.HotelViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class HotelView {

    @FXML
    private TableView<HotelViewModel> tableView;
    @FXML
    private TableColumn<HotelViewModel, String> colNume;
    @FXML
    private TableColumn<HotelViewModel, String> colAdresa;
    @FXML
    private TableColumn<HotelViewModel, String> colNrCamere;
    @FXML
    private TextField txtNume, txtAdresa, txtNrCamere;
    @FXML
    private Label lblMesaj;
    @FXML
    private Button btnAdd, btnUpdate, btnDelete;

    private final HotelViewModel viewModel = new HotelViewModel();

    @FXML
    public void initialize() {

        System.out.println("btnAdd: " + btnAdd);

        colNume.setCellValueFactory(new PropertyValueFactory<>("nume"));
        colAdresa.setCellValueFactory(new PropertyValueFactory<>("adresa"));
        colNrCamere.setCellValueFactory(new PropertyValueFactory<>("nrCamere"));

        tableView.setItems(viewModel.getHoteluri());

        txtNume.textProperty().bindBidirectional(viewModel.numeProperty());
        txtAdresa.textProperty().bindBidirectional(viewModel.adresaProperty());
        txtNrCamere.textProperty().bindBidirectional(viewModel.nrCamereProperty());

        lblMesaj.textProperty().bind(viewModel.mesajProperty());

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                viewModel.selectHotel(newSelection);
            }
        });

        viewModel.addCommand.trigger.bind(btnAdd.pressedProperty());
        viewModel.updateCommand.trigger.bind(btnUpdate.pressedProperty());
        viewModel.deleteCommand.trigger.bind(btnDelete.pressedProperty());
    }

}