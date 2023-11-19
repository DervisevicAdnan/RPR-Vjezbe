package org.etf.unsa.ba;

public class Pobjednik {

    String ime;
    String prezime;
    int brojZnakova;
    Kolekcija kolekcijaImena;
    public Pobjednik(Kolekcija k){
        kolekcijaImena = k;
        String najduzeIme = kolekcijaImena.getNajduzeIme();
        String[] dijelovi = najduzeIme.split(" ");
        ime = dijelovi[0];
        prezime = dijelovi[1];
        brojZnakova = ime.length();

    }

    @Override
    public String toString() {
        return "Pobjednik{" +
                "ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", brojZnakova=" + brojZnakova +
                '}';
    }
}
