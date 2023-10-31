import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //System.out.print("Unesite broj: ");

        //Scanner ulaz = new Scanner(System.in)
        for(String s:args) {
            double br = Double.parseDouble(s);

            Racun racun = new Racun();

            System.out.println("Faktorijel cijelog dijela unesenog broja: " + racun.faktorijel((int) br));

            System.out.println("Sinus: " + racun.sinus(br));
        }
    }
}