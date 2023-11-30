import java.time.*;
public class Spotkanie {
    final LocalTime NAJWCZESNIEJ = LocalTime.of(8,0);
    String opis;
    LocalTime czasPoczatku;
    LocalTime czasKonca;
    String priorytet;

    public Spotkanie(String opis, LocalTime czasPoczatku, LocalTime czasKonca, String priorytet) {
        this.opis = opis;
        this.czasPoczatku = czasPoczatku;
        this.czasKonca = czasKonca;
        this.priorytet = priorytet;
    }
    public boolean czyWczesnie(){
        if (czasPoczatku.isBefore(NAJWCZESNIEJ)) return true;
        return false;
    }
    public String naString(){//zamieniamy sobie na stringi zeby mozna bylo wypisac
        return "opis: " + opis
                + " czasPoczatku: " + czasPoczatku
                + " czas konca: " + czasKonca
                + " priorytet: "+ priorytet;
    }

    public String getPriorytet(){
        return priorytet;
    }

}
