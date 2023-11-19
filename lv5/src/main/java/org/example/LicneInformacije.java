package org.example;

public class LicneInformacije{
    private String ime;
    private String prezime;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String predstavi(){
        return "Ime: "+ime+"; Prezime: "+prezime+"; ";
    }
}
