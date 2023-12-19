package com.example.lv78;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class KorisniciModelTest {

    static KorisniciModel model;

    @BeforeEach
    void postavi(){
        model=new KorisniciModel();
    }

    @Test
    void testGetKorisnici() {

        ArrayList<Korisnik> korisnici = new ArrayList<>();
        korisnici.add(new Korisnik("Mujo","Mujic","mujomujic@gmail.com","mujaga","M"));
        korisnici.add(new Korisnik("Haso","Hasic","hasohasic@gmail.com","hasokralj","lozinka"));
        korisnici.add(new Korisnik("Zlatan","Mujic","zlatanmujic@gmail.com","zlatan101","12345678"));
        korisnici.add(new Korisnik("Tarik","Mujic","tarikmujic@gmail.com","tare","01012001"));
        int i=0;
        for(Korisnik k : model.getKorisnici()){
            assertEquals(k,korisnici.get(i));
            i++;
        }
    }

    @Test
    void setKorisnici() {
        ObservableList<Korisnik> korisnici = FXCollections.observableArrayList();
        korisnici.add(new Korisnik("Mujo","Mujic","mujomujic@gmail.com","mujaga","M"));
        korisnici.add(new Korisnik("Haso","Hasic","hasohasic@gmail.com","hasokralj","lozinka"));
        model.setKorisnici(korisnici);
        assertEquals(model.getKorisnici().size(),2);
    }


    @Test
    void setGetTrenutniKorisnik() {
        Korisnik t=new Korisnik();
        t.setIme("Amir");
        model.setTrenutniKorisnik(t);
        assertEquals(model.getTrenutniKorisnik().getIme(),t.getIme());
    }

    @Test
    void napuni() {
        model.napuni();
        assertEquals(model.getKorisnici().size(),4);

    }
}