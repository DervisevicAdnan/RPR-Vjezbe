public class FiksniBroj extends TelefonskiBroj{

    public enum Grad{
        TRAVNIK(),
        ORASJE,
        ZENICA,
        SARAJEVO(033),
        LIVNO,
        TUZLA,
        MOSTAR,
        BIHAC,
        GORAZDE,
        SIROKIBRIJEG,
        BRCKO,
        ;

        Grad(int i) {
        }
    }
    @Override
    public String ispisi(){

    }

    @Override
    public int hashCode(){

    }
}
