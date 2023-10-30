public class Racun {
    private Long brojRacuna;
    private Osoba korisnikRacuna;
    private boolean odobrenjePrekoracenja;
    private Double stanjeRacuna;
    private Double prekoracenje;

    public Racun(Long brojRacuna, Osoba korisnikRacuna) {
        this.brojRacuna = brojRacuna;
        this.korisnikRacuna = korisnikRacuna;
        odobrenjePrekoracenja=false;
        prekoracenje=0.0;
    }

    public void odobriPrekoracenje(Double iznos){
        prekoracenje=iznos;
        odobrenjePrekoracenja=true;
    }

    private boolean provjeriOdobrenjePrekoracenja(Double iznos){
        return odobrenjePrekoracenja && stanjeRacuna-iznos>-prekoracenje;
    }
    public boolean izvrsiUplatu(Double uplata){
        stanjeRacuna+=uplata;
        return true;
    }

    public boolean izvrsiIsplatu(Double isplata){
        if(isplata<=stanjeRacuna||provjeriOdobrenjePrekoracenja(isplata)){
            stanjeRacuna-=isplata;
            return true;
        }
        return false;
    }

}
