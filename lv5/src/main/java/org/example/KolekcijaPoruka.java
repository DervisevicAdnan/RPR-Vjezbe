package org.example;

import java.util.ArrayList;

public class KolekcijaPoruka {
    ArrayList<String> kolekcija;
    public KolekcijaPoruka(ArrayList<PredstaviKlasa> n){
        kolekcija=new ArrayList<>();
        for (PredstaviKlasa i:n){
            kolekcija.add(i.predstavi());
        }
    }

    public ArrayList<String> getKolekcija() {
        return kolekcija;
    }
}
