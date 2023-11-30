import java.util.ArrayList;
public class Kalendarz {
    ArrayList<ArrayList<Spotkanie>> dziennik = new ArrayList<>();//arraylista arraylist[[spotaknie1,spotkanie2...],[spotkanie1,spotkanie2...],...]
    public Kalendarz(int days) {
        for (int i = 0; i < days; i++) {
            dziennik.add(new ArrayList<>());//[[1],[2]...[days]] ustawiamy sobie ile chcemy list(dni) w kalendarzu
        }
    }
    //Spotkanie spotkanie = new Spotkanie("Opis", LocalTime.of(8, 0), LocalTime.of(9, 0), "High");
    public void addSpotkanie(int dzien,Spotkanie spotkanie){
        dziennik.get(dzien).add(spotkanie);//dodajemy obiekt spotkanie do [[tutaj,tutaj2,..]]
    }
    public void removeSpotkanie(int dzien, Spotkanie spotkanie){
        dziennik.get(dzien).remove(spotkanie);//usuwamy na tej samej zasadzie co dodajemy
    }
    public ArrayList<Spotkanie> getSpotkanie(int dzien){
        return dziennik.get(dzien);//liste na indexie dzien [[dzien],[dzien2]]
    }
}
