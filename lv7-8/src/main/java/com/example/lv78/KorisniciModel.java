package com.example.lv78;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KorisniciModel {
    ObservableList<Korisnik> korisnici = FXCollections.observableArrayList();
    ObjectProperty<Korisnik> trenutniKorisnik = new SimpleObjectProperty<Korisnik>(new Korisnik("","","","",""));

    public ObservableList<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(ObservableList<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public Korisnik getTrenutniKorisnik() {
        return trenutniKorisnik.get();
    }

    public ObjectProperty<Korisnik> trenutniKorisnikProperty() {
        return trenutniKorisnik;
    }

    public void setTrenutniKorisnik(Korisnik trenutniKorisnik) {
        this.trenutniKorisnik.set(trenutniKorisnik);
    }

    public void napuni(){
        korisnici.add(new Korisnik("Mujo","Mujic","mujomujic@gmail.com","mujaga","M"));
        korisnici.add(new Korisnik("Haso","Hasic","hasohasic@gmail.com","hasokralj","lozinka"));
        korisnici.add(new Korisnik("Zlatan","Mujic","zlatanmujic@gmail.com","zlatan101","12345678"));
        korisnici.add(new Korisnik("Tarik","Mujic","tarikmujic@gmail.com","tare","01012001"));
    }
}
