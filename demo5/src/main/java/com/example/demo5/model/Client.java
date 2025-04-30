package com.example.demo5.model;

public class Client {
    private int id;
    private String nume;
    private String telefon;
    private String email;

    // Constructori
    public Client(int id, String nume, String telefon, String email) {
        this.id = id;
        this.nume = nume;
        this.telefon = telefon;
        this.email = email;
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

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
