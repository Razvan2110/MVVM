package com.example.demo5.model;

public class Camera {
    private int id;
    private int idHotel;
    private String numarCamera;
    private double pret;
    private String facilitati;

    public Camera(int id, int idHotel, String numarCamera, double pret, String facilitati) {
        this.id = id;
        this.idHotel = idHotel;
        this.numarCamera = numarCamera;
        this.pret = pret;
        this.facilitati = facilitati;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdHotel() { return idHotel; }
    public void setIdHotel(int idHotel) { this.idHotel = idHotel; }

    public String getNumarCamera() { return numarCamera; }
    public void setNumarCamera(String numarCamera) { this.numarCamera = numarCamera; }

    public double getPret() { return pret; }
    public void setPret(double pret) { this.pret = pret; }

    public String getFacilitati() { return facilitati; }
    public void setFacilitati(String facilitati) { this.facilitati = facilitati; }
}