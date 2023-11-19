package org.etf.unsa.ba;

import java.util.ArrayList;

public class KolekcijaImenaIPrezimena implements Kolekcija{

    ArrayList<String> imena;
    ArrayList<String> prezimena;

    public KolekcijaImenaIPrezimena(){
        imena=new ArrayList<>();
        prezimena=new ArrayList<>();
    }
    public KolekcijaImenaIPrezimena(ArrayList<String> imena,ArrayList<String> prezimena){
        this.imena=imena;
        this.prezimena=prezimena;
    }
    public void dodajImePrezime(String ime,String prezime){
        imena.add(ime);
        prezimena.add(prezime);
    }
    public int getIndexNajduzegPara(){
        int maks=0;
        for (int i=0;i<imena.size();i++){
            if (imena.get(i).length()+prezimena.get(i).length()>imena.get(maks).length()+prezimena.get(maks).length())
                maks=i;
        }
        return maks;
    }

    public String getImeiPrezime(int i){
        if(i>=0&&i<imena.size()) return imena.get(i)+" "+prezimena.get(i);
        return null;
    }

    @Override
    public String getNajduzeIme() {
        return getImeiPrezime(getIndexNajduzegPara());
    }
}
