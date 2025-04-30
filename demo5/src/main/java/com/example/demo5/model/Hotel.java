package com.example.demo5.model;


public class Hotel {
    private int id;
    private String nume;
    private String adresa;
    private int nrCamere;

    // Constructori
    public Hotel(int id, String nume, String adresa, int nrCamere) {
        this.id = id;
        this.nume = nume;
        this.adresa = adresa;
        this.nrCamere = nrCamere;
    }

    // Getter și setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getNrCamere() {
        return nrCamere;
    }

    public void setNrCamere(int nrCamere) {
        this.nrCamere = nrCamere;
    }
}