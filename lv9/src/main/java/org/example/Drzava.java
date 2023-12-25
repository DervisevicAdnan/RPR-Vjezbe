package org.example;

public class Drzava {
    private int id;
    private String naziv;
    private String glavniGrad;

    public Drzava() {
    }

    public Drzava(int id, String naziv, String glavniGrad) {
        this.id = id;
        this.naziv = naziv;
        this.glavniGrad = glavniGrad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getGlavniGrad() {
        return glavniGrad;
    }

    public void setGlavniGrad(String glavniGrad) {
        this.glavniGrad = glavniGrad;
    }
}
