package org.etf.unsa.ba;

import java.util.ArrayList;

public class KolekcijaImena implements Kolekcija{
    ArrayList<String> imePrezime;

    public KolekcijaImena(){
        imePrezime=new ArrayList<>();
    }
    public KolekcijaImena(ArrayList<String> n){
        imePrezime=new ArrayList<>(n);
    }
    public void setImePrezime(ArrayList<String> s){
        imePrezime=s;
    }
    public void dodajImePrezime(String s){
        imePrezime.add(s);
    }
    @Override
    public String getNajduzeIme() {
        String maks=imePrezime.getFirst();
        for (String i:imePrezime){
            if(i.length()>maks.length()) maks=i;
        }
        return maks;
    }

}
