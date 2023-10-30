import java.util.ArrayList;
import java.util.List;

public class Banka {
    private Long brojRacuna;
    private List<Korisnik> korisnici;
    private List<Uposlenik> uposlenici;

    public Banka() {
        korisnici=new ArrayList<Korisnik>();
        uposlenici=new ArrayList<Uposlenik>();
    }
    public Korisnik kreirajNovogKorisnika(String ime,String prezime){
        korisnici.add(new Korisnik(ime,prezime));
        return korisnici.getLast();
    }
    public Uposlenik kreirajNovogUposlenika(String ime, String prezime){
        uposlenici.add(new Uposlenik(ime,prezime));
        return uposlenici.getLast();
    }
    public Racun kreirajRacunZaKorisnika(Korisnik k){
        Racun r=new Racun(brojRacuna,k);
        k.dodajRacun(r);
        return r;
    }
}
