package ba.unsa.etf.rpr;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.*;
import java.util.ArrayList;


public class LaptopDaoJSONFile implements LaptopDao{
    private File file;
    private ArrayList<Laptop> laptopi;

    public LaptopDaoJSONFile() {
        file = new File("laptopi.json");
        laptopi = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file,laptopi);
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
        /*try {
            FileOutputStream fos = new FileOutputStream(file, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonGenerator g = objectMapper.getFactory().createGenerator((OutputStream) oos);
            objectMapper.writeValue(g, laptop);
            g.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException();
        }*/
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            ArrayList<Laptop> ll;
            if(file.exists()&&file.isFile()){
                CollectionType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Laptop.class);
                ll = mapper.readValue(file, type);
                //ll=mapper.readValue(file, ArrayList.class);
            }else ll=new ArrayList<>();
            ll.add(laptop);
            mapper.writeValue(file,ll);
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
        this.laptopi=new ArrayList<>(laptopi);
    }

    @Override
    public ArrayList<Laptop> vratiPodatkeIzDatoteke() {
        ArrayList<Laptop> data=new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            if(!file.exists()||!file.isFile()) return data;
            CollectionType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Laptop.class);
            data = mapper.readValue(file, type);
        } catch(Exception e) {
            System.out.println("Greška: "+e);
        }
        return data;
    }
}
