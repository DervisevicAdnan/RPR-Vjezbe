package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static GeografijaDAO geo;

    public static void main(String[] args) {
        geo = GeografijaDAO.getInstance();

        Scanner ulaz = new Scanner(System.in);
        while (true){
            System.out.println("Ukucajte broj ispred zeljene opcije kako biste je odabrali");
            System.out.println("1. Ispisi sve gradove iz baze");
            System.out.println("2. Ispisi glavni grad odredjene drzave");
            System.out.println("3. Dodaj grad u bazu");
            System.out.println("4. Dodaj drzavu u bazu (Prvo dodajte glavni grad u bazu)");
            System.out.println("5. Obrisi drzavu i sve gradove u toj drzavi iz baze");
            System.out.println("6. Izmijeni grad");
            System.out.println("7. Vrati bazu u pocetno stanje");
            System.out.println("8. Kraj programa");

            switch (ulaz.nextLine()){
                case "1":
                    ispisiGradove();
                    break;
                case "2":
                    glavniGrad();
                    break;
                case "3":
                    dodajGrad();
                    break;
                case "4":
                    dodajDrzavu();
                    break;
                case "5":
                    obrisiDrzavu();
                    break;
                case "6":
                    izmijeniGrad();
                    break;
                case "7":
                    geo.regenerisiBazu();
                    break;
                case "8":
                    return;
                default:
                    System.out.println("Pogresan unos, molimo ponovite");
                    break;
            }
        }
    }

    public static void izmijeniGrad(){
        Scanner ulaz = new Scanner(System.in);
        System.out.println("Unesite naziv grada cije podatke zelite izmijeniti");
        String naziv = ulaz.nextLine();
        Grad trazeni=null;
        ArrayList<Grad> gradovi=geo.gradovi();
        for(Grad g : gradovi){
            if(g.getNaziv().equalsIgnoreCase(naziv)){
                trazeni = g;
                break;
            }
        }
        if(trazeni == null) System.out.println("Trazeni grad nije u bazi");
        else{
            System.out.println("Unesite nove podatke za grad");
            System.out.println("Unesite naziv grada");
            trazeni.setNaziv(ulaz.nextLine());
            System.out.println("Unesite broj stanovnika");
            trazeni.setBrojStanovnika(ulaz.nextInt());
            ulaz.nextLine();
            System.out.println("Unesite drzavu u kojoj se grad nalazi");
            trazeni.setDrzava(ulaz.nextLine());
            geo.izmijeniGrad(trazeni);
        }

    }

    public static void obrisiDrzavu(){
        Scanner ulaz = new Scanner(System.in);
        System.out.println("Unesite naziv drzave");
        geo.obrisiDrzavu(ulaz.nextLine());
    }

    public static void dodajDrzavu(){
        Scanner ulaz = new Scanner(System.in);
        Drzava drzava = new Drzava();
        System.out.println("Unesite naziv drzave");
        drzava.setNaziv(ulaz.nextLine());
        System.out.println("Unesite naziv glavnog grada");
        drzava.setGlavniGrad(ulaz.nextLine());
        geo.dodajDrzavu(drzava);
    }

    public static void ispisiGradove(){
        ArrayList<Grad> gradovi=geo.gradovi();
        for(Grad g : gradovi){
            System.out.println(g.getNaziv()+" ("+g.getDrzava()+") - "+g.getBrojStanovnika());
        }
    }

    public static void glavniGrad(){
        System.out.println("Unesite ime drzave ciji glavni grad vas interesuje");
        Scanner ulaz = new Scanner(System.in);
        Grad grad = geo.glavniGrad(ulaz.nextLine());
        if(grad!=null) System.out.println("Glavni grad drzave "+grad.getDrzava()+" je "+grad.getNaziv());
        else System.out.println("Nepostojeca država");
    }

    public static void dodajGrad(){
        Scanner ulaz = new Scanner(System.in);
        Grad grad=new Grad();
        System.out.println("Unesite naziv grada");
        grad.setNaziv(ulaz.nextLine());
        System.out.println("Unesite broj stanovnika");
        grad.setBrojStanovnika(ulaz.nextInt());
        ulaz.nextLine();
        System.out.println("Unesite naziv drzave u kojoj se grad nalazi");
        grad.setDrzava(ulaz.nextLine());
        geo.dodajGrad(grad);
    }
}