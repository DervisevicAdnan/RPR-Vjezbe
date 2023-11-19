package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static Scanner ulaz;
    static ArrayList<InformacijeOStudentu> studenti;
    static ArrayList<InformacijeONastavniku> nastavnici;
    static ArrayList<Predmet> predmeti;
    public static InformacijeOStudentu unesiStudenta(){
        InformacijeOStudentu s=new InformacijeOStudentu();
        System.out.println("Unesite ime studenta: ");
        s.setIme(ulaz.nextLine());
        System.out.println("Unesite prezime studenta: ");
        s.setPrezime(ulaz.nextLine());
        System.out.println("Unesite indeks studenta: ");
        s.setBrojIndexa(ulaz.nextLine());
        System.out.println("Unesite godinu studija: ");
        s.setGodinaStudija(ulaz.nextLine());
        return s;
    }
    public static InformacijeONastavniku unesiNastavnika(){
        InformacijeONastavniku s=new InformacijeONastavniku();
        System.out.println("Unesite ime nastavnika: ");
        s.setIme(ulaz.nextLine());
        System.out.println("Unesite prezime nastavnika: ");
        s.setPrezime(ulaz.nextLine());
        System.out.println("Unesite titulu: ");
        s.setTitula(ulaz.nextLine());
        return s;
    }

    public static Predmet unesiPredmet(){
        Predmet p=new Predmet();
        System.out.println("Unesite naziv predmeta: ");
        p.setNaziv(ulaz.nextLine());
        System.out.println("Unesite opis predmeta: ");
        p.setOpis(ulaz.nextLine());
        return p;
    }

    public static InformacijeONastavniku odaberiNastavnika(){
        InformacijeONastavniku p=unesiNastavnika();
        for(int i=0;i<nastavnici.size();i++){
            if(nastavnici.get(i).predstavi().equalsIgnoreCase(p.predstavi())){
                return nastavnici.get(i);
            }
        }
        return null;
    }
    public static LicneInformacije odaberiOsobu(){
        LicneInformacije l=null;
        System.out.println("Unesite zeljeni odabir:\n" +
                "1. student\n" +
                "2. nastavnik");
        int opcija=ulaz.nextInt();
        ulaz.nextLine();
        if(opcija==1){
            System.out.println("Unesite broj indeksa: ");
            String ind=ulaz.nextLine();
            for(InformacijeOStudentu i:studenti){
                if(i.getBrojIndexa().equals(ind)) l=i;
            }
        }else if(opcija==2){
            l=odaberiNastavnika();
        }else System.out.println("Nepostojeca opcija");
        if(l==null) System.out.println("Trazena osoba nije u sistemu");
        return l;
    }

    public static Predmet odaberiPredmet(){
        System.out.println("Unesite naziv predmeta: ");
        String imep=ulaz.nextLine();
        for (int i=0;i<predmeti.size();i++){
            if(predmeti.get(i).getNaziv().equalsIgnoreCase(imep)) return predmeti.get(i);
        }
        System.out.println("Nepostojeci predmet");
        return null;
    }

    public static void ocijeniPredmet(){
        System.out.println("Vasi podaci:");
        LicneInformacije l=odaberiOsobu();
        if(l!=null){
            System.out.println("Ocjenjivanje:");
            Predmet p=odaberiPredmet();
            if (p==null) return;
            System.out.println("Unesite ocjenu:");
            int ocjena=ulaz.nextInt();
            ulaz.nextLine();
            try {
                p.dodajOcjenu(l.ocijeni(ocjena));
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void ocijeniNastavnika(){
        System.out.println("Vasi podaci:");
        LicneInformacije l=odaberiOsobu();
        if(l instanceof InformacijeONastavniku){
            System.out.println("Nemate mogucnost ocjenjivanja nastavnika");
            return;
        }
        if(l!=null){
            System.out.println("Ocjenjivanje:");
            InformacijeONastavniku p=odaberiNastavnika();
            if(p==null) {
                System.out.println("Nastavnik nije u sistemu");
                return;
            }
            System.out.println("Unesite ocjenu:");
            int ocjena=ulaz.nextInt();
            try {
                p.dodajOcjenu(l.ocijeni(ocjena));
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void ispisiOcjene(ArrayList<Ocjena> ocjene){
        for (Ocjena i:ocjene){
            System.out.println(i.getOsoba().predstavi()+"ocijenio sa: "+i.getOcjena());
        }
    }


    public static void main(String[] args) {
        ulaz = new Scanner(System.in);
        int opcija;
        studenti=new ArrayList<>();
        nastavnici=new ArrayList<>();
        predmeti=new ArrayList<>();
        System.out.println("DOBRO DOSLI");
        while(true){
            System.out.println("Unesite broj ispred zeljene opcije\n" +
                    "1. Dodaj studenta\n" +
                    "2. Dodaj nastavnika\n" +
                    "3. Izlistaj studenate\n" +
                    "4. Izlistaj nastavnike\n" +
                    "5. Dodaj predmet\n" +
                    "6. Izlistaj predmete\n" +
                    "7. Izlistaj kolekciju poruka\n" +
                    "8. Ocjenjivanje predmeta\n" +
                    "9. Ocjenjivanje nastavnika\n" +
                    "10. Izlistaj ocjene predmeta\n" +
                    "11. Izlistaj ocjene nastavnika\n" +
                    "0. Kraj programa");
            opcija=ulaz.nextInt();
            ulaz.nextLine();
            switch(opcija){
                case 1:
                    studenti.add(unesiStudenta());
                    break;
                case 2:
                    nastavnici.add(unesiNastavnika());
                    break;
                case 3:
                    for (InformacijeOStudentu i:studenti){
                        System.out.println(i.predstavi());
                    }
                    break;
                case 4:
                    for (InformacijeONastavniku i:nastavnici){
                        System.out.println(i.predstavi());
                    }
                    break;
                case 5:
                    predmeti.add(unesiPredmet());
                    break;
                case 6:
                    for (Predmet i:predmeti){
                        System.out.println(i.predstavi());
                    }
                    break;
                case 7:
                    ArrayList<PredstaviKlasa> ap=new ArrayList<>();
                    ap.addAll(studenti);
                    ap.addAll(nastavnici);
                    ap.addAll(predmeti);
                    KolekcijaPoruka kp = new KolekcijaPoruka(ap);
                    for(String i: kp.getKolekcija()){
                        System.out.println(i);
                    }
                    break;
                case 8:
                    ocijeniPredmet();
                    break;
                case 9:
                    ocijeniNastavnika();
                    break;
                case 10:
                    Predmet p=odaberiPredmet();
                    if(p!=null) ispisiOcjene(p.getOcjene());
                    break;
                case 11:
                    InformacijeONastavniku n=odaberiNastavnika();
                    if(n!=null) ispisiOcjene(n.getOcjene());
                    break;
                case 0:
                    System.out.println("Dovidjenja");
                    break;
                default:
                    System.out.println("Nepostojeca opcija!");
            }
            if (opcija==0) break;
        }

/*
        LicneInformacije o1 =new LicneInformacije();
        InformacijeOStudentu s1 =new InformacijeOStudentu();
        InformacijeONastavniku n1 =new InformacijeONastavniku();
        InformacijeOStudentu s2 =new InformacijeOStudentu();
        InformacijeONastavniku n2 =new InformacijeONastavniku();
        o1.setIme("Mujo");
        o1.setIme("Mujic");
        s1.setIme("Suljo");
        s1.setIme("Suljic");
        s1.setBrojIndexa("12345");
        s1.setGodinaStudija("prva");
        n1.setIme("Mustafa");
        n1.setIme("Mustafic");
        n1.setTitula("Redovni profesor");
        s2.setIme("Dervis");
        s2.setIme("Dervisevic");
        s2.setBrojIndexa("12565");
        s2.setGodinaStudija("prva");
        n2.setIme("Musa");
        n2.setIme("Music");
        n2.setTitula("Vanredni profesor");

        Predmet p1=new Predmet();
        p1.setNaziv("Matematika");
        p1.setOpis("**********");

        KolekcijaPoruka kp=new KolekcijaPoruka((ArrayList<PredstaviKlasa>) Arrays.asList(o1,s1,s2,n1,n2,p1));
        for(String i:kp.getKolekcija()){
            System.out.println(i);
        }

        p1.dodajOcjenu(s1.ocijeni(5));
        p1.dodajOcjenu(n1.ocijeni(6));
        for(Ocjena i: p1.getOcjene()){
            System.out.println(i.getOsoba().predstavi()+"ocjena: "+i.getOcjena());
        }
*/

    }
}