package com.example.demo5.view;

import com.example.demo5.viewmodel.CameraRezervataViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.NumberStringConverter;

public class CameraRezervataView {

    @FXML
    private TableView<CameraRezervataViewModel> tableView;
    @FXML
    private TableColumn<CameraRezervataViewModel, Integer> colId, colIdClient, colIdHotel, colIdCamera;
    @FXML
    private TableColumn<CameraRezervataViewModel, String> colStatusRezervare;
    @FXML
    private TableColumn<CameraRezervataViewModel, java.time.LocalDate> colCheckin, colCheckout;
    @FXML
    private TextField txtIdClient, txtIdHotel, txtIdCamera, txtStatusRezervare;
    @FXML
    private DatePicker dpCheckin, dpCheckout;
    @FXML
    private Label lblMesaj;
    @FXML
    private Button btnAdd, btnUpdate, btnDelete;

    private final CameraRezervataViewModel viewModel = new CameraRezervataViewModel();

    @FXML
    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colIdClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        colIdHotel.setCellValueFactory(new PropertyValueFactory<>("idHotel"));
        colIdCamera.setCellValueFactory(new PropertyValueFactory<>("idCamera"));
        colCheckin.setCellValueFactory(new PropertyValueFactory<>("checkin"));
        colCheckout.setCellValueFactory(new PropertyValueFactory<>("checkout"));
        colStatusRezervare.setCellValueFactory(new PropertyValueFactory<>("statusRezervare"));


        tableView.setItems(viewModel.getCamereRezervate());

        Bindings.bindBidirectional(txtIdClient.textProperty(), viewModel.idClientProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(txtIdHotel.textProperty(), viewModel.idHotelProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(txtIdCamera.textProperty(), viewModel.idCameraProperty(), new NumberStringConverter());
        txtStatusRezervare.textProperty().bindBidirectional(viewModel.statusRezervareProperty());
        dpCheckin.valueProperty().bindBidirectional(viewModel.checkinProperty());
        dpCheckout.valueProperty().bindBidirectional(viewModel.checkoutProperty());


        lblMesaj.textProperty().bind(viewModel.mesajProperty());


        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                viewModel.selectCameraRezervata(newSelection);
            }
        });


        viewModel.addCommand.trigger.bind(btnAdd.pressedProperty());
        viewModel.updateCommand.trigger.bind(btnUpdate.pressedProperty());
        viewModel.deleteCommand.trigger.bind(btnDelete.pressedProperty());
    }
}