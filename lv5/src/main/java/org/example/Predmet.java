package org.example;

import java.util.ArrayList;

public class Predmet implements PredstaviKlasa{
    private String naziv;
    private String opis;
    private ArrayList<Ocjena> ocjene;

    public Predmet(){
        ocjene=new ArrayList<>();
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public ArrayList<Ocjena> getOcjene(){
        return ocjene;
    }

    public void dodajOcjenu(Ocjena ocjena){
        ocjene.add(ocjena);
    }

    @Override
    public String predstavi(){
        return "Naziv: "+naziv+"; Opis: "+opis+"; ";
    }
}
