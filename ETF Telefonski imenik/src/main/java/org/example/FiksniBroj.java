package org.example;

import java.util.Objects;

public class FiksniBroj extends TelefonskiBroj{
    private Grad grad;
    private String broj;

    public FiksniBroj(Grad grad, String broj){
        if(grad == null) throw new Izuzetak("Neispravan pozivni broj");
        this.grad = grad;
        this.broj = broj;
    }

    @Override
    public String ispisi(){
        if(grad == null || broj == null)
            return null;
        return grad.getPozivniBroj()+"/"+broj;
    }

    @Override
    public int hashCode(){
        return Objects.hash(grad,broj);
    }

    public Grad getGrad() {
        return grad;
    }

    public String getBroj() {
        return broj;
    }
}
