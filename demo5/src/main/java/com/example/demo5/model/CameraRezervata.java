package com.example.demo5.model;

import java.time.LocalDate;

public class CameraRezervata {
    private int id;
    private int idClient;
    private int idHotel;
    private int idCamera;
    private LocalDate checkin;
    private LocalDate checkout;
    private String statusRezervare;

    // Constructori
    public CameraRezervata(int id, int idClient, int idHotel, int idCamera, LocalDate checkin, LocalDate checkout, String statusRezervare) {
        this.id = id;
        this.idClient = idClient;
        this.idHotel = idHotel;
        this.idCamera = idCamera;
        this.checkin = checkin;
        this.checkout = checkout;
        this.statusRezervare = statusRezervare;
    }

    // Getter și setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public int getIdCamera() {
        return idCamera;
    }

    public void setIdCamera(int idCamera) {
        this.idCamera = idCamera;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public String getStatusRezervare() {
        return statusRezervare;
    }

    public void setStatusRezervare(String statusRezervare) {
        this.statusRezervare = statusRezervare;
    }
}
