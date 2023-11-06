package org.example;

import java.util.*;
public class Imenik {
    private Map<String, TelefonskiBroj> brojevi;

    public Imenik() {
        this.brojevi = new HashMap<String, TelefonskiBroj>();
    }

    public Map<String, TelefonskiBroj> getBrojevi() {
        return brojevi;
    }

    public void setBrojevi(Map<String, TelefonskiBroj> brojevi) {
        this.brojevi = brojevi;
    }

    public void dodaj(String ime, TelefonskiBroj broj){
        brojevi.put(ime,broj);
    }

    public String dajBroj(String ime){
        TelefonskiBroj broj = this.brojevi.get(ime);
        if(broj != null)
            return broj.ispisi();
        return null;
    }

    public String dajIme(TelefonskiBroj broj){
        for(Map.Entry<String,TelefonskiBroj> entry : this.brojevi.entrySet()){
            if(entry.getValue().ispisi().equals(broj.ispisi()))
                return entry.getKey();
        }
        return null;
    }

    public String naSlovo(char c){
        StringBuilder builder = new StringBuilder();
        int br=1;
        for(Map.Entry<String,TelefonskiBroj> entry : this.brojevi.entrySet()){
            if(entry.getKey().startsWith(String.valueOf(c))){
                builder.append(br);
                builder.append(". ");
                builder.append(entry.getKey())
                        .append(" - ")
                        .append(entry.getValue().ispisi())
                        .append('\n');
            }
            br++;
        }
        return builder.toString();
    }

    public Set<String> izGrada(Grad g){
        Set<String> results = new TreeSet<String>();
        for(Map.Entry<String,TelefonskiBroj> entry : this.brojevi.entrySet()){
            if(jelIzGrada(entry.getValue(),g))
                results.add(entry.getKey());
        }
        return results;
    }

    private boolean jelIzGrada(TelefonskiBroj broj,Grad g){
        if(broj instanceof FiksniBroj)
            return g.equals(((FiksniBroj) broj).getGrad());
        return false;
    }

    public Set<TelefonskiBroj> izGradaBrojevi(Grad g){
        Set<TelefonskiBroj> results = new TreeSet<TelefonskiBroj>(new Comparator<TelefonskiBroj>(){
            @Override
            public int compare(TelefonskiBroj b1, TelefonskiBroj b2){return b1.ispisi().compareTo(b2.ispisi());}
        });
        for (Map.Entry<String,TelefonskiBroj> entry : this.brojevi.entrySet()){
            if(jelIzGrada(entry.getValue(),g))
                results.add(entry.getValue());
        }
        return results;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int br=1;
        for(Map.Entry<String,TelefonskiBroj> entry : this.brojevi.entrySet()){
            builder.append(br)
                    .append(". ")
                    .append(entry.getKey())
                    .append(" - ")
                    .append(entry.getValue().ispisi())
                    .append('\n');
            br++;
        }
        return builder.toString();
    }
}
