package ba.unsa.etf.rpr;

import java.io.*;
import java.util.ArrayList;

public class LaptopDaoSerializableFile implements LaptopDao,Serializable{
    final private File file ;
    private ArrayList<Laptop> laptopi;

    public LaptopDaoSerializableFile() {
        file = new File("laptopi.txt");
        laptopi = new ArrayList<>();
        try {
            ObjectOutputStream izlaz = new ObjectOutputStream(
                    new FileOutputStream(file));
            izlaz.close();
        } catch(Exception e) {
            System.out.println("Greška: "+e);
        }
    }


    @Override
    public void dodajLaptopUListu(Laptop laptop) {
        laptopi.add(laptop);
    }

    public void dodajLaptopUFile(Laptop laptop){
        try {
            boolean append = true;
            MyObjectOutputStream izlaz;
            if (append && file.exists()) {
                izlaz = new MyObjectOutputStream(
                        new FileOutputStream(file, true)) {
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                };
            } else {
                izlaz = new MyObjectOutputStream(
                        new FileOutputStream(file));
            }
            izlaz.writeObject(laptop);
            izlaz.close();
        } catch(Exception e) {
            System.out.println("Greška: "+e);
        }
    }


    /*@Override
    public void dodajLaptopUFile(Laptop laptop){
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream izlaz = new ObjectOutputStream(fos);
            izlaz.writeObject(laptop);
            izlaz.close();
            fos.close();
        } catch(Exception e) {
            System.out.println("Greška: "+e);
        }

        /*FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        laptopi.add(laptop);
        os.writeObject(laptopi);
        os.close();
        fos.close();
        return laptop;
*/
    //}

    @Override
    public Laptop getLaptop(String procesor){
        for(Laptop l:laptopi){
            if(l.getProcesor().equalsIgnoreCase(procesor)) return l;
        }
        throw new NeodgovarajuciProcesorException();
    }

    @Override
    public void napuniListu(ArrayList<Laptop> laptopi) {
        this.laptopi=new ArrayList<>(laptopi);
    }

    @Override
    public ArrayList<Laptop> vratiPodatkeIzDatoteke() {
        ArrayList<Laptop> l = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (fis.available()>0) {
                l.add((Laptop) ois.readObject());
            }
            ois.close();
            fis.close();
        } catch(Exception e) {
            System.out.println("Greška: "+e);
        }
        return l;
    }
}
