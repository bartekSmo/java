import java.time.*;
public class Spotkanie {
    static final LocalTime NAJWCZESNIEJ = LocalTime.of(8,0);
    private String opis;
    private LocalTime czasPoczatku;
    private LocalTime czasKonca;
    private String priorytet;

    public Spotkanie(String opis, LocalTime czasPoczatku, LocalTime czasKonca, String priorytet) {
        this.opis = opis;
        this.czasPoczatku = czasPoczatku;
        this.czasKonca = czasKonca;
        this.priorytet = priorytet;
    }

    public String toString(){//zamieniamy sobie na stringi zeby mozna bylo wypisac
        return "opis: " + opis
                + " czas Poczatku: " + czasPoczatku
                + " czas konca: " + czasKonca
                + " priorytet: " + priorytet;
    }
    public String getPriorytet(){
        return priorytet;
    }
    public LocalTime getCzasKonca(){return czasKonca;}
    public LocalTime getCzasPoczatku(){return czasPoczatku;}
}
