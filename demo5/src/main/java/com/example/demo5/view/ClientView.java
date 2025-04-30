package com.example.demo5.view;

import com.example.demo5.viewmodel.ClientViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClientView {

    @FXML
    private TableView<ClientViewModel> tableView;
    @FXML
    private TableColumn<ClientViewModel, String> colNume;
    @FXML
    private TableColumn<ClientViewModel, String> colTelefon;
    @FXML
    private TableColumn<ClientViewModel, String> colEmail;
    @FXML
    private TextField txtNume, txtTelefon, txtEmail;
    @FXML
    private Label lblMesaj;
    @FXML
    private Button btnAdd, btnUpdate, btnDelete;

    private final ClientViewModel viewModel = new ClientViewModel();

    @FXML
    public void initialize() {

        colNume.setCellValueFactory(new PropertyValueFactory<>("nume"));
        colTelefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));


        tableView.setItems(viewModel.getClienti());


        txtNume.textProperty().bindBidirectional(viewModel.numeProperty());
        txtTelefon.textProperty().bindBidirectional(viewModel.telefonProperty());
        txtEmail.textProperty().bindBidirectional(viewModel.emailProperty());


        lblMesaj.textProperty().bind(viewModel.mesajProperty());

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                viewModel.selectClient(newSelection);
            }
        });

        viewModel.addCommand.trigger.bind(btnAdd.pressedProperty());
        viewModel.updateCommand.trigger.bind(btnUpdate.pressedProperty());
        viewModel.deleteCommand.trigger.bind(btnDelete.pressedProperty());
    }
}