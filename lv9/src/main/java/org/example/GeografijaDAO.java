package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GeografijaDAO {
    private static GeografijaDAO instance = null;
    private Connection conn;
    private PreparedStatement dajGradove, dajDrzavu, dajGlavniGradDrzave, obrisiGradoveDrzave, psObrisiDrzavu,
    psDodajGrad, psDodajGradNull, psDodajDrzavu, updateGrad, commit;
    private GeografijaDAO() {
        try {

            conn = DriverManager.getConnection("jdbc:sqlite::resource:baza.db");
            try {
                dajGradove = conn.prepareStatement("SELECT g.naziv, g.broj_stanovnika, IFNULL(d.naziv,'Nepoznato'), g.id FROM grad g LEFT JOIN drzava d ON g.drzava=d.id ORDER BY broj_stanovnika DESC;");
            } catch(SQLException e) {
                regenerisiBazu();
                try {
                    dajGradove = conn.prepareStatement("SELECT g.naziv, g.broj_stanovnika, IFNULL(d.naziv,'Nepoznato'), g.id FROM grad g LEFT JOIN drzava d ON g.drzava=d.id ORDER BY broj_stanovnika DESC;");
                } catch(SQLException e1) {
                    e1.printStackTrace();
                }
            }

            dajDrzavu = conn.prepareStatement("SELECT d.id, d.naziv, g.naziv FROM drzava d, grad g WHERE d.glavni_grad=g.id AND d.naziv=?");
            dajGlavniGradDrzave = conn.prepareStatement("SELECT * FROM grad g, drzava d WHERE g.id=d.glavni_grad AND d.naziv=?");
            obrisiGradoveDrzave = conn.prepareStatement("DELETE FROM grad WHERE drzava=?");
            psObrisiDrzavu = conn.prepareStatement("DELETE FROM drzava WHERE naziv=?");
            psDodajGrad = conn.prepareStatement("INSERT INTO grad(naziv, broj_stanovnika, drzava) VALUES(?,?,?)");
            psDodajGradNull = conn.prepareStatement("INSERT INTO grad(naziv, broj_stanovnika) VALUES(?,?)");
            psDodajDrzavu = conn.prepareStatement("INSERT INTO drzava(naziv, glavni_grad) VALUES(?,(SELECT g.id FROM grad g WHERE g.naziv=?))");
            updateGrad = conn.prepareStatement("UPDATE grad SET naziv=?, broj_stanovnika=?, drzava=? WHERE id=?");
            commit = conn.prepareStatement("COMMIT");
        } catch (SQLException e) {
            System.out.println("Nemoguca konekcija na bazu");
        }
    }

    public void regenerisiBazu(){
        Scanner ulaz = null;
        try {
            // Dohvatite input stream datoteke iz resources foldera
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("baza.db.sql");

            if (inputStream == null) {
                throw new FileNotFoundException("Datoteka 'baza.db.sql' nije pronađena u resources folderu.");
            }
            ulaz = new Scanner(inputStream);

            //ulaz = new Scanner(new FileInputStream("baza.db.sql"));
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if ( sqlUpit.length() > 1 && sqlUpit.charAt( sqlUpit.length()-1 ) == ';') {
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ne postoji SQL datoteka... nastavljam sa praznom bazom");
        }
    }

    public static GeografijaDAO getInstance(){
        if(instance == null) instance = new GeografijaDAO();
        return instance;
    }
    public static void removeInstance(){
        try {
            instance.conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        instance=null;
    }

    public ArrayList<Grad> gradovi(){
        ArrayList<Grad> gradovi = new ArrayList<>();
        try {
            ResultSet result = dajGradove.executeQuery();
            while(result.next()){
                Grad grad = new Grad();
                grad.setId(result.getInt(4));
                grad.setNaziv(result.getString(1));
                grad.setBrojStanovnika(result.getInt(2));
                grad.setDrzava(result.getString(3));
                gradovi.add(grad);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return gradovi;
    }

    public Grad glavniGrad(String drzava){
        try {
            dajGlavniGradDrzave.setString(1,drzava);
            ResultSet result = dajGlavniGradDrzave.executeQuery();
            if(result.next()){
                return new Grad(result.getInt(1),result.getString(2),result.getInt(3),drzava);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void obrisiDrzavu(String drzava){
        try {
            dajDrzavu.setString(1,drzava);
            ResultSet result = dajDrzavu.executeQuery();
            if(!result.next()) return;
            int id=result.getInt(1);
            obrisiGradoveDrzave.setInt(1,id);
            obrisiGradoveDrzave.executeUpdate();
            psObrisiDrzavu.setString(1,drzava);
            psObrisiDrzavu.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dodajGrad(Grad grad){
        try {
            Drzava d = nadjiDrzavu(grad.getDrzava());
            if(d==null){
                System.out.println("Neuspjesno povezivanje sa drzavom. Drzava ne postoji u bazi. Nakon unosa drzave izmijenite podatke");
                psDodajGradNull.setString(1,grad.getNaziv());
                psDodajGradNull.setInt(2,grad.getBrojStanovnika());
                psDodajGradNull.executeUpdate();
            }else{
                psDodajGrad.setString(1,grad.getNaziv());
                psDodajGrad.setInt(2,grad.getBrojStanovnika());
                psDodajGrad.setInt(3,d.getId());
                psDodajGrad.executeUpdate();
            }
            //commit.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void dodajDrzavu(Drzava drzava){
        try {
            psDodajDrzavu.setString(1,drzava.getNaziv());
            psDodajDrzavu.setString(2,drzava.getGlavniGrad());
            psDodajDrzavu.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void izmijeniGrad(Grad grad){
        try {
            updateGrad.setString(1,grad.getNaziv());
            updateGrad.setInt(2,grad.getBrojStanovnika());
            updateGrad.setInt(3,nadjiDrzavu(grad.getDrzava()).getId());
            updateGrad.setInt(4,grad.getId());
            updateGrad.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public Drzava nadjiDrzavu(String drzava){
        try {
            dajDrzavu.setString(1,drzava);
            ResultSet result = dajDrzavu.executeQuery();
            if(result.next()){
                return new Drzava(result.getInt(1),result.getString(2),result.getString(3));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
