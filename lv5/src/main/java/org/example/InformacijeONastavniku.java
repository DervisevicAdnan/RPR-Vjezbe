package org.example;

public class InformacijeONastavniku extends LicneInformacije{
    private String titula;

    public String getTitula() {
        return titula;
    }

    public void setTitula(String titula) {
        this.titula = titula;
    }

    public String predstavi(){
        return super.predstavi()+"Titula: "+titula+"; ";
    }
}
