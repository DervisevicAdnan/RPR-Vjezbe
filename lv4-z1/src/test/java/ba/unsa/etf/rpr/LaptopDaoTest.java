package ba.unsa.etf.rpr;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LaptopDaoTest {

    public static ArrayList<Laptop> l;

    @BeforeAll
    static void kreiranje(){
        l=new ArrayList<>();
        Laptop l1=new Laptop();
        l1.setBrend("HP");
        l1.setModel("EliteBook");
        l1.setCijena(330);
        l1.setProcesor("Intel i5 5300u");
        l1.setRam(8);
        l1.setHdd(0);
        l1.setSsd(240);
        l1.setGrafickaKartica("Intel HD Graphics 5500");
        l1.setVelicinaEkrana(12.5);
        l.add(l1);
        l1.setBrend("HP");
        l1.setModel("ProBook");
        l1.setCijena(800);
        l1.setProcesor("Intel i7 8650u");
        l1.setRam(8);
        l1.setHdd(0);
        l1.setSsd(256);
        l1.setGrafickaKartica("Intel HD Graphics");
        l1.setVelicinaEkrana(15.6);
        l.add(l1);
        l1.setBrend("Lenovo");
        l1.setModel("ThinkPad");
        l1.setCijena(500);
        l1.setProcesor("Intel i5 6300u");
        l1.setRam(8);
        l1.setHdd(0);
        l1.setSsd(256);
        l1.setGrafickaKartica("Intel HD Graphics");
        l1.setVelicinaEkrana(13.3);
        l.add(l1);
    }

    @Test
    void dodajLaptopUListu() {
        for(Laptop i:l){
            LaptopDao lp=mock(LaptopDaoSerializableFile.class);
            lp.dodajLaptopUListu(i);
            verify(lp,times(1)).dodajLaptopUListu(i);
        }
        for(Laptop i:l){
            LaptopDao lp=mock(LaptopDaoJSONFile.class);
            lp.dodajLaptopUListu(i);
            verify(lp,times(1)).dodajLaptopUListu(i);
        }
        for(Laptop i:l){
            LaptopDao lp=mock(LaptopDaoXMLFile.class);
            lp.dodajLaptopUListu(i);
            verify(lp,times(1)).dodajLaptopUListu(i);
        }
    }

    @Test
    void dodajLaptopUFile() {
        for(Laptop i:l){
            LaptopDao lp=mock(LaptopDaoSerializableFile.class);
            lp.dodajLaptopUFile(i);
            verify(lp,times(1)).dodajLaptopUFile(i);
        }
        for(Laptop i:l){
            LaptopDao lp=mock(LaptopDaoJSONFile.class);
            lp.dodajLaptopUFile(i);
            verify(lp,times(1)).dodajLaptopUFile(i);
        }
        for(Laptop i:l){
            LaptopDao lp=mock(LaptopDaoXMLFile.class);
            lp.dodajLaptopUFile(i);
            verify(lp,times(1)).dodajLaptopUFile(i);
        }
    }

    @Test
    void getLaptop() {
        LaptopDao lp =new LaptopDaoSerializableFile();
        for(Laptop i:l){
            lp.dodajLaptopUListu(i);
            assertEquals(lp.getLaptop(i.getProcesor()),i);
        }
        assertThrowsExactly(NeodgovarajuciProcesorException.class,()->{lp.getLaptop("bla bla");});
        LaptopDao lp2=new LaptopDaoJSONFile();
        for(Laptop i:l){
            lp2.dodajLaptopUListu(i);
            assertEquals(lp2.getLaptop(i.getProcesor()),i);
        }
        assertThrowsExactly(NeodgovarajuciProcesorException.class,()->{lp2.getLaptop("bla bla");});
        LaptopDao lp3=new LaptopDaoXMLFile();
        for(Laptop i:l){
            lp3.dodajLaptopUListu(i);
            assertEquals(lp3.getLaptop(i.getProcesor()),i);
        }
        assertThrowsExactly(NeodgovarajuciProcesorException.class,()->{lp3.getLaptop("bla bla");});
    }

    @Test
    void napuniListu() {
        LaptopDao lp=mock(LaptopDaoSerializableFile.class);
        lp.napuniListu(l);
        verify(lp, times(1)).napuniListu(l);
        lp=mock(LaptopDaoJSONFile.class);
        lp.napuniListu(l);
        verify(lp, times(1)).napuniListu(l);
        lp=mock(LaptopDaoXMLFile.class);
        lp.napuniListu(l);
        verify(lp, times(1)).napuniListu(l);
    }

    @Test
    void vratiPodatkeIzDatoteke() {
        LaptopDao lp=new LaptopDaoSerializableFile();
        assertEquals(lp.vratiPodatkeIzDatoteke().size(),0);
        for (Laptop i:l){
            lp.dodajLaptopUFile(i);
        }
        assertArrayEquals(lp.vratiPodatkeIzDatoteke().toArray(), l.toArray());
        lp=new LaptopDaoJSONFile();
        assertEquals(lp.vratiPodatkeIzDatoteke().size(),0);
        for (Laptop i:l){
            lp.dodajLaptopUFile(i);
        }
        assertArrayEquals(lp.vratiPodatkeIzDatoteke().toArray(), l.toArray());
        lp=new LaptopDaoXMLFile();
        assertEquals(lp.vratiPodatkeIzDatoteke().size(),0);
        for (Laptop i:l){
            lp.dodajLaptopUFile(i);
        }
        assertArrayEquals(lp.vratiPodatkeIzDatoteke().toArray(), l.toArray());
    }
}