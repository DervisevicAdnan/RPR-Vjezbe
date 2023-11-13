package ba.unsa.etf.rpr;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

public class LaptopDaoXMLFile implements LaptopDao {
    private File file;
    private ArrayList<Laptop> laptopi;

    public LaptopDaoXMLFile() {
        file = new File("laptopi.xml");
        laptopi = new ArrayList<>();
        try {
            XMLEncoder encoder = new XMLEncoder(
                    new BufferedOutputStream(
                            new FileOutputStream(file)));
            encoder.writeObject(laptopi);
            encoder.close();
        } catch(Exception e) {
            System.out.println("Greška: "+e);
        }
    }

    @Override
    public void dodajLaptopUListu(Laptop laptop) {
        laptopi.add(laptop);
    }

    @Override
    public void dodajLaptopUFile(Laptop laptop) {
        ArrayList<Laptop> lp=vratiPodatkeIzDatoteke();
        lp.add(laptop);
        try {
            XMLEncoder encoder = new XMLEncoder(
                    new BufferedOutputStream(
                            new FileOutputStream(file)));
            encoder.writeObject(lp);
            encoder.close();
        } catch(Exception e) {
            System.out.println("Greška: "+e);
        }
    }

    @Override
    public Laptop getLaptop(String procesor) {
        for(Laptop l:laptopi){
            if(l.getProcesor().equalsIgnoreCase(procesor)) return l;
        }
        throw new NeodgovarajuciProcesorException();
    }

    @Override
    public void napuniListu(ArrayList<Laptop> laptopi) {
        this.laptopi.addAll(laptopi);
    }

    @Override
    public ArrayList<Laptop> vratiPodatkeIzDatoteke() {
        ArrayList<Laptop> l = new ArrayList<>();
        try {
            XMLDecoder decoder = new XMLDecoder(
                    new BufferedInputStream(
                            new FileInputStream(file)));

            while (true) {
                try {
                    l=((ArrayList<Laptop>) decoder.readObject());
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }
            }

            decoder.close();
        } catch(Exception e) {
            System.out.println("Greška: "+e);
        }
        return l;
    }
}
