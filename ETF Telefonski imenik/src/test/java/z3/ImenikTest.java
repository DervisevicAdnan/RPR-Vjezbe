package z3;

import org.example.FiksniBroj;
import org.example.Grad;
import org.example.Imenik;
import org.example.TelefonskiBroj;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

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
    public void test1(){
        Imenik i = Mockito.mock(Imenik.class);
        Mockito.when(i.dajBroj("Amir")).thenReturn("Nema ga");

        String test = i.dajBroj("Amir");
        assertEquals(test,"Nema ga");
    }

    @Test
    public void test2(){
        Map<String, TelefonskiBroj> mapa = Mockito.mock(Map.class);
        Mockito.when(mapa.get("Amir")).thenReturn(new FiksniBroj(Grad.BIJELJINA,"089-422"));
        imenik.setBrojevi(mapa);

        String br = imenik.dajBroj("Amir");
        assertNotEquals(br,"055/098-423");
        assertEquals(br,"055/089-422");
    }

}