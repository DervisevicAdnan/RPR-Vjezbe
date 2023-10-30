import java.util.List;

public class Racunanje {

    public static Double min(List<Double> lista){
        Double m=lista.getFirst();
        for(Double i:lista){
            if(m>i) m=i;
        }
        return m;
    }

    public static Double max(List<Double> lista){
        Double m=lista.getFirst();
        for(Double i:lista){
            if(m<i) m=i;
        }
        return m;
    }

    public static Double mean(List<Double> lista){
        double suma=0;
        for(Double i:lista){
            suma = suma + i.doubleValue();
        }
        return suma/lista.size();
    }

    public static Double deviacija(List<Double> lista){
        Double mn=mean(lista);
        double dev=0;
        for(Double i:lista){
            dev = dev + Math.pow(i-mn,2);
        }
        return Math.sqrt(dev/lista.size());
    }

    public static void printanjeStatistike(List<Double> lista){
        System.out.println("Min: "+min(lista));
        System.out.println("Max: "+max(lista));
        System.out.println("Mean: "+mean(lista));
        System.out.println("Standardna devijacija: "+deviacija(lista));
    }


}
