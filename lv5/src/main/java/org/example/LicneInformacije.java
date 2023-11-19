package org.example;

public class LicneInformacije implements PredstaviKlasa,MozeOcijeniti{
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

    @Override
    public Ocjena ocijeni(int x){
        return new Ocjena(this,x);
    }

    @Override
    public String predstavi(){
        return "Ime: "+ime+"; Prezime: "+prezime+"; ";
    }
}
