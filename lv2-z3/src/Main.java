import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ulaz=new Scanner(System.in);
        List<Double> lista = new ArrayList<Double>();
        while(true) {
            String s = ulaz.nextLine();
            if("stop".equalsIgnoreCase(s.trim())) break;
            try{
                Double d=Double.parseDouble(s);
                lista.add(d);
            }catch (Exception e){
                System.out.println("Nije unesen broj");
            }
        }
        Racunanje.printanjeStatistike(lista);
    }
}