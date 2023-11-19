package org.example;

public class Ocjena {
    private LicneInformacije osoba;
    private int ocjena;

    public Ocjena(LicneInformacije osoba, int ocjena){
        this.osoba=osoba;
        setOcjena(ocjena);
    }

    public LicneInformacije getOsoba() {
        return osoba;
    }

    public int getOcjena() {
        return ocjena;
    }

    public void setOcjena(int n){

        if(n>=0 && n<=10) ocjena=n;
        else throw new RuntimeException("Neispravna ocjena");
    }
}
