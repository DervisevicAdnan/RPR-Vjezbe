package z2;

import org.example.FiksniBroj;
import org.example.Grad;
import org.example.Imenik;
import org.example.Izuzetak;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImenikTest {

    private static Imenik imenik = new Imenik();

    @BeforeAll
    public static void pocetno(){
        imenik.dodaj("Adnan",new FiksniBroj(Grad.SARAJEVO,"123-123"));
        imenik.dodaj("Mustafa",new FiksniBroj(Grad.ZENICA,"432-143"));
        imenik.dodaj("Lejla",new FiksniBroj(Grad.MOSTAR,"324-867"));
        imenik.dodaj("Amir",new FiksniBroj(Grad.BIJELJINA,"098-423"));
    }

    @Test
    public void dajBrojPostojeciKorisnik(){
        String s = imenik.dajBroj("Adnan");
        assertEquals(s,"033/123-123");
    }

    @Test
    public void dajBrojNepostojeciKorisinik(){
        String s = imenik.dajBroj("Admir");
        assertNull(s);
    }

    @Test
    public void dodajDajBroj(){
        imenik.dodaj("Sulejman",new FiksniBroj(Grad.SARAJEVO,"000-000"));
        String s = imenik.dajBroj("Sulejman");
        assertEquals(s,"033/000-000");
    }

    @Test
    public void IzuzetakTest(){
        assertThrows(Izuzetak.class,()->{new FiksniBroj(null,"111-111");});
        assertThrowsExactly(Izuzetak.class,()->{new FiksniBroj(null,"153-564");},"Neispravan pozivni broj");
    }

}