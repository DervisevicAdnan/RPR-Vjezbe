package com.example.lv78;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {
    private KorisniciModel model;
    public ListView<Korisnik> listKorisnici;
    public TextField fldIme;
    public TextField fldPrezime;
    public TextField fldEmail;
    public TextField fldKorisnickoIme;
    public TextField fldLozinka;
    public Controller(KorisniciModel model) {
        this.model = model;
    }

    private void vezi(){
        fldIme.textProperty().bindBidirectional(model.getTrenutniKorisnik().imeProperty());
        fldPrezime.textProperty().bindBidirectional(model.getTrenutniKorisnik().prezimeProperty());
        fldEmail.textProperty().bindBidirectional(model.getTrenutniKorisnik().emailProperty());
        fldKorisnickoIme.textProperty().bindBidirectional(model.getTrenutniKorisnik().korisnickoImeProperty());
        fldLozinka.textProperty().bindBidirectional(model.getTrenutniKorisnik().lozinkaProperty());
    }

    private void odvezi(){
        fldIme.textProperty().unbindBidirectional(model.getTrenutniKorisnik().imeProperty());
        fldPrezime.textProperty().unbindBidirectional(model.getTrenutniKorisnik().prezimeProperty());
        fldEmail.textProperty().unbindBidirectional(model.getTrenutniKorisnik().emailProperty());
        fldKorisnickoIme.textProperty().unbindBidirectional(model.getTrenutniKorisnik().korisnickoImeProperty());
        fldLozinka.textProperty().unbindBidirectional(model.getTrenutniKorisnik().lozinkaProperty());
    }

    public void initialize() {
        listKorisnici.setItems(model.getKorisnici());
        vezi();

        listKorisnici.getSelectionModel().selectedItemProperty().addListener((obs,
                                                                              oldKorisnik, newKorisnik) -> {
            if (oldKorisnik != null) {
                odvezi();
            }

            if (newKorisnik == null) {
                model.setTrenutniKorisnik(new Korisnik("","","","",""));
            }
            else {
                model.setTrenutniKorisnik(newKorisnik);
            }
            vezi();
            listKorisnici.refresh();

        });
    }

    public void btnDodajKlicked(ActionEvent event){
        Korisnik korisnik = new Korisnik("","","","","");
        odvezi();
        model.getKorisnici().add(korisnik);
        model.setTrenutniKorisnik(korisnik);
        listKorisnici.refresh();
        listKorisnici.getSelectionModel().select(korisnik);
    }

    public void btnKrajKlicked(ActionEvent event){
        Node n = (Node) event.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

}