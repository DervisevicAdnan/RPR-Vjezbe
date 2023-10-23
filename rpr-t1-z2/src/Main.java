import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ulaz = new Scanner(System.in);
        System.out.print("Unesite broj: ");
        int n=ulaz.nextInt();
        System.out.println("Brojevi od 1 do "+n+" koji su djeljivi sa zbirom svojih cifara su: ");
        for(int i=1;i<=n;i++){
            if(djeljivSaZbiromSvojihCifara(i)){
                System.out.println(i);
            }
        }
    }

    public static boolean djeljivSaZbiromSvojihCifara(int n){
        if(n%sumaCifara(n)==0){
            return true;
        }
        return false;
    }

    public static int sumaCifara(int n){
        int s=0;
        while(n>0){
            s=s+n%10;
            n=n/10;
        }
        return s;
    }
}