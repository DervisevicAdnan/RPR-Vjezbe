package org.example;

import java.util.Scanner;
import java.util.Set;

public class Program {
    public static Scanner scanner = new Scanner(System.in);
    public static Imenik imenik = new Imenik();

    public static void main(String[] args) {
        popuniPodatke();

        while(true){
            System.out.println("Unesite broj ispred zeljene funkcionalnosti " +
                    "(1. dodaj, 2. dajBroj, " +
                    "3. dajIme, 4. naSlovo, " +
                    "5. izGrada, 6. izGradaBrojevi, " +
                    "7. imenik, 8. izlaz)");
            int opcija = scanner.nextInt();
            scanner.nextLine();
            switch (opcija){
                case 1:
                    dodajBroj();
                    break;
                case 2:
                    dajBroj();
                    break;
                case 3:
                    dajIme();
                    break;
                case 4:
                    naSlovo();
                    break;
                case 5:
                    izGrada();
                    break;
                case 6:
                    izGradaBrojevi();
                    break;
                case 7:
                    ispisiImenik();
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Neispravan unos!");
            }
        }
    }

    private static void dajBroj(){
        System.out.println("Unesite ime: ");
        String ime = scanner.nextLine();
        String rez = imenik.dajBroj(ime);
        if(rez == null)
            System.out.println("Nema u imeniku");
        else
            System.out.println(rez);
    }

    private static void dodajBroj(){
        System.out.println("Unesite ime: ");
        String ime = scanner.nextLine();
        TelefonskiBroj br = unesiBrojTelefona();
        imenik.dodaj(ime,br);
    }

    private static void ispisiImenik(){
        System.out.println(imenik.toString());
    }

    private static void izGradaBrojevi(){
        System.out.println("Unesite ime grada:");
        String grad = scanner.nextLine();
        try {
            Grad g = Grad.valueOf(grad);
            Set<TelefonskiBroj> grbr = imenik.izGradaBrojevi(g);
            for(TelefonskiBroj b : grbr){
                System.out.println(b.ispisi());
            }
        }catch (Exception e){
            System.out.println("Greska");
            return;
        }
    }

    private static void izGrada(){
        System.out.println("Unesite ime grada:");
        String grad = scanner.nextLine();
        try {
            Grad g = Grad.valueOf(grad);
            Set<String> izg = imenik.izGrada(g);
            System.out.println(izg);
        }catch (Exception e){
            System.out.println("Greska");
            return;
        }
    }

    private static void naSlovo(){
        System.out.println("Unesite prvo slovo imena:");
        String c = scanner.nextLine();
        String r = imenik.naSlovo(c.toCharArray()[0]);
        System.out.println(r);
    }

    private static void dajIme(){
        TelefonskiBroj br = unesiBrojTelefona();
        String ime = imenik.dajIme(br);
        if(ime == null)
            System.out.println("Ne postoji u imeniku");
        else
            System.out.println("Vlasnik broja "+br.ispisi()+" je "+ime);
    }

    private static TelefonskiBroj unesiBrojTelefona(){
        System.out.println("Unesite tip broja" +
                "(1-fiksni, 2-mobilni, 3-medjunarodni)");
        int tip = scanner.nextInt();
        scanner.nextLine();
        switch (tip){
            case 1:
                System.out.println("Unesite pozivni broj: ");
                String pozivniBr = scanner.nextLine();
                System.out.println("Unesite broj: ");
                String broj = scanner.nextLine();
                return new FiksniBroj(Grad.izPozivnog(pozivniBr),broj);
            case 2:
                System.out.println("Unesite mrezu: ");
                int mreza = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Unesite broj: ");
                String mobilniBroj = scanner.nextLine();
                return new MobilniBroj(mreza,mobilniBroj);
            case 3:
                System.out.println("Unesite kod drzave: ");
                String kod = scanner.nextLine();
                System.out.println("Unesite broj: ");
                String medBr = scanner.nextLine();
                return new MedunarodniBroj(kod,medBr);
        }
        return null;
    }

    private static void popuniPodatke(){
        imenik.dodaj("Adnan",new FiksniBroj(Grad.SARAJEVO,"123-123"));
        imenik.dodaj("Mustafa",new FiksniBroj(Grad.ZENICA,"432-143"));
        imenik.dodaj("Lejla",new FiksniBroj(Grad.MOSTAR,"324-867"));
        imenik.dodaj("Amir",new FiksniBroj(Grad.BIJELJINA,"098-423"));
    }
}