package com.example.demo5.view;

import com.example.demo5.viewmodel.CameraViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.NumberStringConverter;

public class CameraView {

    @FXML
    private TableView<CameraViewModel> tableView;
    @FXML
    private TableColumn<CameraViewModel, Integer> colIdHotel;
    @FXML
    private TableColumn<CameraViewModel, String> colNumarCamera;
    @FXML
    private TableColumn<CameraViewModel, Double> colPret;
    @FXML
    private TableColumn<CameraViewModel, String> colFacilitati;
    @FXML
    private TextField txtIdHotel;
    @FXML
    private TextField txtNumarCamera;
    @FXML
    private TextField txtPret; 
    @FXML
    private TextField txtFacilitati;
    @FXML
    private DatePicker dpCheckIn, dpCheckOut;
    @FXML
    private Label lblMesaj;
    @FXML
    private Button btnShowAvailable, btnSaveCsv, btnSaveDoc, btnAdd, btnUpdate, btnDelete;
    @FXML
    private Button btnShowHotelRooms; // Declare the new button

    private final CameraViewModel viewModel = new CameraViewModel();

    @FXML
    public void initialize() {

        colIdHotel.setCellValueFactory(new PropertyValueFactory<>("idHotel"));
        colNumarCamera.setCellValueFactory(new PropertyValueFactory<>("numarCamera"));
        colPret.setCellValueFactory(new PropertyValueFactory<>("pret"));
        colFacilitati.setCellValueFactory(new PropertyValueFactory<>("facilitati"));


        tableView.setItems(viewModel.getCamere());

        txtIdHotel.textProperty().bindBidirectional(viewModel.idHotelProperty(), new NumberStringConverter());
        txtNumarCamera.textProperty().bindBidirectional(viewModel.numarCameraProperty());
        txtPret.textProperty().bindBidirectional(viewModel.pretProperty(), new NumberStringConverter());
        txtFacilitati.textProperty().bindBidirectional(viewModel.facilitatiProperty());
        dpCheckIn.valueProperty().bindBidirectional(viewModel.checkInProperty());
        dpCheckOut.valueProperty().bindBidirectional(viewModel.checkOutProperty());
        lblMesaj.textProperty().bind(viewModel.mesajProperty());

        viewModel.showAvailableCommand.trigger.bind(btnShowAvailable.pressedProperty());
        viewModel.saveCsvCommand.trigger.bind(btnSaveCsv.pressedProperty());
        viewModel.saveDocCommand.trigger.bind(btnSaveDoc.pressedProperty());
        viewModel.addCommand.trigger.bind(btnAdd.pressedProperty());
        viewModel.updateCommand.trigger.bind(btnUpdate.pressedProperty());
        viewModel.deleteCommand.trigger.bind(btnDelete.pressedProperty());

        viewModel.showHotelRoomsCommand.trigger.bind(btnShowHotelRooms.pressedProperty());


        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            viewModel.selectCamera(newSelection);
        });
        btnShowAvailable.setOnAction(e -> {
            viewModel.showAvailableCommand.trigger.set(true);
            tableView.refresh();
        });
    }
}