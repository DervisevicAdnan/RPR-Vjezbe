package org.example;

import java.util.ArrayList;

public class InformacijeONastavniku extends LicneInformacije{
    private String titula;
    ArrayList<Ocjena> ocjene;

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

    public String predstavi(){
        return super.predstavi()+"Titula: "+titula+"; ";
    }
}
