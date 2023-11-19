package org.etf.unsa.ba;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        KolekcijaImena kolekcijaImena = new KolekcijaImena();
        kolekcijaImena.dodajImePrezime("Ime1 Prezime1");
        kolekcijaImena.dodajImePrezime("DrugoIme DrugoPrezime");
        Pobjednik pobjednik1 = new Pobjednik(kolekcijaImena);
        System.out.println("Pobjednik1 je: " + pobjednik1.ime + " " + pobjednik1.prezime);
        
        KolekcijaImenaIPrezimena kolekcijaImenaIPrezimena = new KolekcijaImenaIPrezimena();
        kolekcijaImenaIPrezimena.dodajImePrezime("Ime1", "Prezime1");
        kolekcijaImenaIPrezimena.dodajImePrezime("DrugoIme", "DrugoPrezime");
        Pobjednik pobjednik2 = new Pobjednik(kolekcijaImenaIPrezimena);
        System.out.println("Pobjednik2 je: " + pobjednik2.ime + " " + pobjednik2.prezime);
    }
}