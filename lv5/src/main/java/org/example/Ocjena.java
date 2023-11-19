package org.example;

public class Ocjena {
    private LicneInformacije osoba;
    int ocjena;

    public Ocjena(LicneInformacije osoba, int ocjena){
        this.osoba=osoba;
        if(ocjena>=0&&ocjena<=10) this.ocjena=ocjena;
        else this.ocjena=0;
    }

    public void setOcjena(int n){
        if(n>=0 && n<=10) ocjena=n;
    }
}
