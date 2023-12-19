package com.example.lv78;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.framework.junit5.TestFx;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class ControllerTest {
    private TextField imeFld;
    private TextField prezimeFld;
    private TextField korisnickoImeFld;
    private TextField emailFld;
    private PasswordField lozinkaFld;
    @Start
    public void start (Stage stage) throws Exception {
        KorisniciModel model=new KorisniciModel();
        model.napuni();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        fxmlLoader.setController(new Controller(model));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Korisnici");
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @TestFx
    public void test0_startSaPraznim (FxRobot robot) {
        imeFld = robot.lookup("#fldIme").queryAs(TextField.class);
        prezimeFld = robot.lookup("#fldPrezime").queryAs(TextField.class);
        emailFld = robot.lookup("#fldEmail").queryAs(TextField.class);
        korisnickoImeFld = robot.lookup("#fldKorisnickoIme").queryAs(TextField.class);
        lozinkaFld = robot.lookup("#fldLozinka").queryAs(PasswordField.class);
        assertNotNull(imeFld);
        assertEquals("", imeFld.getText());
        assertNotNull(prezimeFld);
        assertEquals("", prezimeFld.getText());
        assertNotNull(emailFld);
        assertEquals("", emailFld.getText());
        assertNotNull(korisnickoImeFld);
        assertEquals("", korisnickoImeFld.getText());
        assertNotNull(lozinkaFld);
        assertEquals("", lozinkaFld.getText());
    }

    @TestFx
    public void test1_napunjenaLista(FxRobot robot){
        ListView<Korisnik> listKorisnici = robot.lookup("#listKorisnici").queryAs(ListView.class);
        assertNotNull(listKorisnici);
        assertEquals(4,listKorisnici.getItems().size());
    }

    @TestFx
    public void test2_odabirKorisnika(FxRobot robot){
        ListView<Korisnik> listView = robot.lookup("#listKorisnici").query();
        for(int i=0;i<1;i++){
            for(int j=0;j<4;j++) {
                listView.getSelectionModel().select(j);
            }
        }
    }

    @TestFx
    public void test3_dodajGumbe(FxRobot robot){
        ListView<Korisnik> listView = robot.lookup("#listKorisnici").query();
        listView.getSelectionModel().select(0);
        //robot.clickOn(listView);
        robot.clickOn("#btnDodaj");
        ListView<Korisnik> listKorisnici = robot.lookup("#listKorisnici").queryAs(ListView.class);
        assertNotNull(listKorisnici);
        assertEquals(5,listKorisnici.getItems().size());
        imeFld = robot.lookup("#fldIme").queryAs(TextField.class);
        prezimeFld = robot.lookup("#fldPrezime").queryAs(TextField.class);
        emailFld = robot.lookup("#fldEmail").queryAs(TextField.class);
        korisnickoImeFld = robot.lookup("#fldKorisnickoIme").queryAs(TextField.class);
        lozinkaFld = robot.lookup("#fldLozinka").queryAs(PasswordField.class);
        assertEquals("", imeFld.getText());
        assertEquals("", prezimeFld.getText());
        assertEquals("", emailFld.getText());
        assertEquals("", korisnickoImeFld.getText());
        assertEquals("", lozinkaFld.getText());
    }

    @TestFx
    public void test4_promjenaKorisnika(FxRobot robot){
        ListView<Korisnik> listView = robot.lookup("#listKorisnici").query();
        listView.getSelectionModel().select(0);
        imeFld = robot.lookup("#fldIme").queryAs(TextField.class);
        prezimeFld = robot.lookup("#fldPrezime").queryAs(TextField.class);
        emailFld = robot.lookup("#fldEmail").queryAs(TextField.class);
        korisnickoImeFld = robot.lookup("#fldKorisnickoIme").queryAs(TextField.class);
        lozinkaFld = robot.lookup("#fldLozinka").queryAs(PasswordField.class);
        assertEquals("Mujo", imeFld.getText());
        assertEquals("Mujic", prezimeFld.getText());
        assertEquals("mujomujic@gmail.com", emailFld.getText());
        assertEquals("mujaga", korisnickoImeFld.getText());
        assertEquals("M", lozinkaFld.getText());
        listView.getSelectionModel().select(2);
        assertEquals("Zlatan", imeFld.getText());
        assertEquals("Mujic", prezimeFld.getText());
        assertEquals("zlatanmujic@gmail.com", emailFld.getText());
        assertEquals("zlatan101", korisnickoImeFld.getText());
        assertEquals("12345678", lozinkaFld.getText());
    }

    @TestFx
    public void test5_promjenaPodataka(FxRobot robot){
        ListView<Korisnik> listView = robot.lookup("#listKorisnici").query();
        listView.getSelectionModel().select(0);
        robot.clickOn("#fldIme");
        robot.press(KeyCode.CONTROL).press(KeyCode.A).release(KeyCode.A).release(KeyCode.CONTROL);
        robot.write("Mustafa");
        imeFld = robot.lookup("#fldIme").queryAs(TextField.class);
        prezimeFld = robot.lookup("#fldPrezime").queryAs(TextField.class);
        emailFld = robot.lookup("#fldEmail").queryAs(TextField.class);
        korisnickoImeFld = robot.lookup("#fldKorisnickoIme").queryAs(TextField.class);
        lozinkaFld = robot.lookup("#fldLozinka").queryAs(PasswordField.class);
        listView.getSelectionModel().select(1);
        listView.getSelectionModel().select(0);
        assertEquals("Mustafa", imeFld.getText());
        assertEquals("Mujic", prezimeFld.getText());
        assertEquals("mujomujic@gmail.com", emailFld.getText());
        assertEquals("mujaga", korisnickoImeFld.getText());
        assertEquals("M", lozinkaFld.getText());
    }
}