package org.example;

import java.util.ArrayList;

public class InformacijeONastavniku extends LicneInformacije{
    private String titula;
    ArrayList<Ocjena> ocjene;

    public InformacijeONastavniku(){
        ocjene=new ArrayList<>();
    }

    public String getTitula() {
        return titula;
    }

    public void setTitula(String titula) {
        this.titula = titula;
    }

    @Override
    public Ocjena ocijeni(int x) {
        return new Ocjena(this,x);
    }

    public ArrayList<Ocjena> getOcjene(){
        return ocjene;
    }

    public void dodajOcjenu(Ocjena ocjena){
        if (ocjena.getOsoba() instanceof InformacijeOStudentu) ocjene.add(ocjena);
        else throw new RuntimeException("Osoba nema mogucnost ocjenjivanja nastavnika");
    }

    public String predstavi(){
        return super.predstavi()+"Titula: "+titula+"; ";
    }
}
