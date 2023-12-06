import java.time.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Kalendarz kalendarz = new Kalendarz(7);
        System.out.println("kalendarz");
        int wybor;
        ArrayList<Spotkanie> dziennik = kalendarz.getSpotkanie(0);
        Spotkanie spotkanieProba = new Spotkanie("obiad",LocalTime.of(8,30),LocalTime.of(8,45),"High");
        Spotkanie spotkanieProba2 = new Spotkanie("sniadanie",LocalTime.of(9,30),LocalTime.of(9,45),"High");
        Spotkanie spotkanieProba3 = new Spotkanie("kolacja",LocalTime.of(10,30),LocalTime.of(10,45),"Low");
        dziennik.add(spotkanieProba);
        dziennik.add(spotkanieProba2);
        dziennik.add(spotkanieProba3);
        do{
            menuInfo();
            wybor = scanner.nextInt();
            menu(scanner, wybor, kalendarz);
        }while (wybor != 0);
    }
    private static void menuInfo(){
        System.out.println("1)pokazanie\n2)stworzenie\n3)usuniecie\n4)priorytet\n5)nie wczesniej\n6)pomiedzy czasami\n7)priorytet + do czasu\n0)Wyjscie");
    }
    private static void menu(Scanner scanner, int wybor,  Kalendarz kalendarz){
        switch (wybor){
            case 1 -> show(kalendarz.getSpotkanie(ktoryDzien(scanner)));
            case 2 -> stworzSpotkanie(scanner,kalendarz);//tworzymy obiekt Spotkanie z danymi od usera
            case 3->{
                int dzien = ktoryDzien(scanner);
                int ktore = ktoreSpotkanie(scanner ,kalendarz, dzien);//musimy wiedziec ktory index z arraylisty z dnia [[spotkanie1,spotkanie2...],[spotkanie1...]]
                if(ktore==-1){System.out.println("nie ma takiego spotkania");break;}
                kalendarz.removeSpotkanie(dzien, kalendarz.getSpotkanie(dzien).get(ktore));//usuwamy znajac dzien i index z listy dnia
            }
            case 4 -> {
                String priorytet = podajPriorytet(scanner);
                show(kalendarz.cleaner(a -> a.getPriorytet().equals(priorytet),kalendarz,ktoryDzien(scanner)));
            }
            case 5 -> {
                LocalTime poczatek = podajCzasy(scanner);
                show(kalendarz.cleaner(a -> a.getCzasPoczatku().isAfter(poczatek),kalendarz,ktoryDzien(scanner)));}
            case 6 ->{
                LocalTime poczatek = podajCzasy(scanner);
                LocalTime koniec = podajCzasy(scanner);
                show(kalendarz.cleaner(a -> (a.getCzasKonca().isBefore(koniec) || a.getCzasKonca().equals(koniec)) && (a.getCzasPoczatku().isAfter(poczatek) || a.getCzasPoczatku().equals(poczatek)),kalendarz,ktoryDzien(scanner)));
            }
            case 7 ->{
                LocalTime koniec = podajCzasy(scanner);
                String priorytet = podajPriorytet(scanner);
                show(kalendarz.cleaner(a -> a.getCzasPoczatku().isBefore(koniec) && a.getPriorytet().equalsIgnoreCase(priorytet) ,kalendarz,ktoryDzien(scanner)));
            }

            case 0 -> System.out.println("bye!");
            default -> System.out.println("zla wartosc");
        }
    }
    private static void show(ArrayList<Spotkanie> dziennik){
        for(Spotkanie wartosci : dziennik){
            System.out.println(wartosci);
        }
    }
    private static void stworzSpotkanie(Scanner scanner, Kalendarz kalendarz){
        int dzien = ktoryDzien(scanner);
        String opis;
        System.out.println("Podaj opis spotkania: ");
        opis = scanner.next();
        System.out.println("Podaj czas zaczecia pozniejszy niz 08:00: ");
        LocalTime czas1 = LocalTime.parse(scanner.next());
        System.out.println("Podaj czas zakonczenia: ");
        LocalTime czas2 = LocalTime.parse(scanner.next());
        System.out.println("Podaj priorytet High,Medium,Low: ");
        String priorytet = scanner.next();
        kalendarz.stworzSpotkanie(opis,czas1,czas2,priorytet, dzien);
    }
    private static LocalTime podajCzasy(Scanner scanner){
        System.out.println("Podaj Godzine: ");
        return LocalTime.parse(scanner.next());
    }
    private  static String podajPriorytet(Scanner scanner){
        System.out.println("Podaj priorytet: High,Medium,Low ");
        return scanner.next();
    }
    private static int ktoreSpotkanie(Scanner scanner, Kalendarz kalendarz, int dzien){
        int ktora;
        System.out.println("podaj ktore spotkanie chcesz usunac");
        ktora = scanner.nextInt();
        if(kalendarz.czyJestTakieSpotkanie(dzien, kalendarz, ktora))
            return ktora;
        return -1;
    }
    private static int ktoryDzien(Scanner scanner){
        int ktory;
        System.out.println("Podaj dzien: ");
        ktory = scanner.nextInt();
        return ktory - 1;
    }
    private static void ktoryPriorytet(Scanner scanner, Kalendarz kalendarz){
        ArrayList<Spotkanie> dziennik = kalendarz.getSpotkanie(ktoryDzien(scanner));
        System.out.println("podaj jaki priorytet High, Medium, Low");
        String jaki = scanner.next();
        ArrayList<Spotkanie> cleanedDziennik = kalendarz.cleanSpotkania(dziennik,jaki);
        show(cleanedDziennik);
    }
}
