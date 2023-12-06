import java.time.LocalTime;
import java.util.ArrayList;
import java.util.function.Predicate;

public class Kalendarz {
    private ArrayList<ArrayList<Spotkanie>> dziennik = new ArrayList<>();//arraylista arraylist[[spotaknie1,spotkanie2...],[spotkanie1,spotkanie2...],...]

    public void dodaniePrzykladowychSpotkan(){
        Spotkanie spotkaniePoniedzialekProba1 = new Spotkanie("sniadanie",LocalTime.of(9,30),LocalTime.of(10, 0),"High");
        Spotkanie spotkaniePoniedzialekProba2 = new Spotkanie("obiad",LocalTime.of(12,30),LocalTime.of(13,0),"High");
        Spotkanie spotkaniePoniedzialekProba5 = new Spotkanie("deser",LocalTime.of(13,30),LocalTime.of(14,45),"Medium");
        Spotkanie spotkaniePoniedzialekProba4 = new Spotkanie("trening",LocalTime.of(15,30),LocalTime.of(17,0),"Low");
        Spotkanie spotkaniePoniedzialekProba6 = new Spotkanie("kino",LocalTime.of(20,30),LocalTime.of(21,0),"Medium");
        Spotkanie spotkaniePoniedzialekProba3 = new Spotkanie("kolacja",LocalTime.of(21,30),LocalTime.of(21,45),"Low");
        dziennik.get(0).add(spotkaniePoniedzialekProba1);
        dziennik.get(0).add(spotkaniePoniedzialekProba2);
        dziennik.get(0).add(spotkaniePoniedzialekProba5);
        dziennik.get(0).add(spotkaniePoniedzialekProba4);
        dziennik.get(0).add(spotkaniePoniedzialekProba6);
        dziennik.get(0).add(spotkaniePoniedzialekProba3);
    }
    public void stworzSpotkanie(String opis, LocalTime czasPoczatku, LocalTime czasKonca, String priotytet,int dzien){
        if(czasPoczatku.isBefore(Spotkanie.NAJWCZESNIEJ)){return;}
        Spotkanie spotkanie = new Spotkanie(opis,czasPoczatku,czasKonca,priotytet);
        dziennik.get(dzien).add(spotkanie);
    }
    public Kalendarz(int days) {
        for (int i = 0; i < days; i++){
            dziennik.add(new ArrayList<>());//[[1],[2]...[days]] ustawiamy sobie ile chcemy list(dni) w kalendarzu
        }
    }
    public void removeSpotkanie(int dzien, Spotkanie spotkanie){
        dziennik.get(dzien).remove(spotkanie);//usuwamy na tej samej zasadzie co dodajemy
    }
    public ArrayList<Spotkanie> getSpotkanie(int dzien){
        return dziennik.get(dzien);//liste na indexie dzien [[dzien],[dzien2]]
    }

    public boolean czyJestTakieSpotkanie(int dzien,int ktore){
        return getSpotkanie(dzien).size() > ktore;
    }
    public ArrayList<Spotkanie> filtrListBy(Predicate<Spotkanie>check,  int wDniu){
        ArrayList<Spotkanie> cleanedArr = new ArrayList<>();
        for(Spotkanie spotkanie : getSpotkanie(wDniu)){
            if(check.test(spotkanie)){
                cleanedArr.add(spotkanie);
            }
        }
        return cleanedArr;
    }
}
