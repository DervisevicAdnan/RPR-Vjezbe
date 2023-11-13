package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    private static Laptop unesiLaptop(){
        Scanner ulaz=new Scanner(System.in);
        Laptop l=new Laptop();
        System.out.print("Unesite brend laptopa: ");
        l.setBrend(ulaz.nextLine());
        System.out.print("Unesite model laptopa: ");
        l.setModel(ulaz.nextLine());
        System.out.print("Unesite cijenu laptopa: ");
        l.setCijena(ulaz.nextDouble());
        ulaz.nextLine();
        System.out.print("Unesite kapacitet RAM memorije u GB: ");
        l.setRam(ulaz.nextInt());
        System.out.print("Unesite kapacitet memorije HDD-a u GB: ");
        l.setHdd(ulaz.nextInt());
        System.out.print("Unesite kapacitet memorije SSD-a u GB: ");
        l.setSsd(ulaz.nextInt());
        ulaz.nextLine();
        System.out.print("Unesite model procesora: ");
        l.setProcesor(ulaz.nextLine());
        System.out.print("Unesite model graficke kartice: ");
        l.setGrafickaKartica(ulaz.nextLine());
        System.out.print("Unesite velicinu ekrana: ");
        l.setVelicinaEkrana(ulaz.nextDouble());
        ulaz.nextLine();
        return l;
    }

    public static void main(String[] args) {
        Scanner ulaz=new Scanner(System.in);
        System.out.println("Odaberite u kojem obliku zelite da bude datoteka u koju se podaci smjestaju:" +
                "\n1 binarna\n2 JSON\n3 XML\n4 kraj programa");
        int dat=ulaz.nextInt();
        LaptopDao l = null;
        while(true) {
            switch (dat) {
                case 1:
                    l = new LaptopDaoSerializableFile();
                    break;
                case 2:
                    l = new LaptopDaoJSONFile();
                    break;
                case 3:
                    l = new LaptopDaoXMLFile();
                    break;
                case 4:
                    System.out.println("Dovidjenja");
                    return;
                default:
                    System.out.println("Pogresan unos, molimo da ponovite");
                    ulaz.nextLine();
            }
            if(dat>=1&&dat<=3) break;
        }
        while (true){
            System.out.println("Odaberite zeljenu funkciju:\n" +
                    "1 dodaj laptop u listu\n2 dodaj laptop u file\n" +
                    "3 daj laptop sa zeljenim procesorom\n" +
                    "4 vrati podatke iz datoteke\n5 kraj programa\n");
            dat=ulaz.nextInt();
            ulaz.nextLine();
            switch (dat){
                case 1:
                    l.dodajLaptopUListu(unesiLaptop());
                    break;
                case 2:
                    l.dodajLaptopUFile(unesiLaptop());
                    break;
                case 3:
                    try {
                        System.out.println("Unesite trazeni model procesora: ");
                        System.out.println(l.getLaptop(ulaz.nextLine()).toString());
                    }catch (NeodgovarajuciProcesorException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    ArrayList<Laptop> lptp=l.vratiPodatkeIzDatoteke();
                    System.out.println(lptp.toString());
                    break;
                case 5:
                    System.out.println("Dovidjenja");
                    return;
                default:
                    System.out.println("Pogresan unos, molimo da ponovite");
                    ulaz.nextLine();
            }
        }
    }
}