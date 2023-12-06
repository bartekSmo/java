import java.time.LocalTime;
import java.util.ArrayList;
import java.util.function.Predicate;

public class Kalendarz {
    ArrayList<ArrayList<Spotkanie>> dziennik = new ArrayList<>();//arraylista arraylist[[spotaknie1,spotkanie2...],[spotkanie1,spotkanie2...],...]
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
    public ArrayList<Spotkanie>cleanSpotkania(ArrayList<Spotkanie> spotkanieWDniu, String priorytetUser){
        ArrayList<Spotkanie>cleanedList=new ArrayList<>();
        for(Spotkanie spotkania : spotkanieWDniu){
            if(spotkania.getPriorytet().equalsIgnoreCase(priorytetUser)){
                cleanedList.add(spotkania);
            }
        }
        return cleanedList;
    }
    public boolean czyJestTakieSpotkanie(int dzien,Kalendarz kalendarz,int ktore){
        return kalendarz.getSpotkanie(dzien).size() > ktore;
    }
    public ArrayList<Spotkanie> cleaner(Predicate<Spotkanie>check,Kalendarz kalendarz,  int wDniu){
        ArrayList<Spotkanie> cleanedArr = new ArrayList<>();
        for(Spotkanie spotkanie : kalendarz.getSpotkanie(wDniu)){
            if(check.test(spotkanie)){
                cleanedArr.add(spotkanie);
            }
        }
        return cleanedArr;
    }
}
