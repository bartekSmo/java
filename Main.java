import java.time.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Kalendarz kalendarz = new Kalendarz(7);
        System.out.println("kalendarz");
        int wybor;
        kalendarz.dodaniePrzykladowychSpotkan();
        do{
            menuInfo();
            wybor = scanner.nextInt();
            menu(scanner, wybor, kalendarz);
        }while (wybor != 0);
    }
    private static void menuInfo(){
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("1) pokazanie spotkan w podanym dniu");
        System.out.println("2) Stworzenie spotkania");
        System.out.println("3) usuniecie podanego spotkania w danym dniu");
        System.out.println("4) wyswietlenie spotkania w danym dniu z podanym priorytetem");
        System.out.println("5) wyswietlenie spotkania ktore zaczyna sie nie wczesniej niz podany czas");
        System.out.println("6) wyswietlenie spotkania ktorego czas jest pomiedzy danymi godzinami");
        System.out.println("7) wyswietlenie spotkania ktore koncza sie przed podana godzina i maja okreslony priorytet");
        System.out.println("0) koniec\n");
        System.out.println("--------------------------------------------------------------------------------------------");
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
                show(kalendarz.filtrListBy(a -> a.getPriorytet().equalsIgnoreCase(priorytet),ktoryDzien(scanner)));
            }
            case 5 -> {
                LocalTime poczatek = podajCzasy(scanner);
                show(kalendarz.filtrListBy(a -> a.getCzasPoczatku().isAfter(poczatek) || a.getCzasPoczatku().equals(poczatek),ktoryDzien(scanner)));}
            case 6 ->{
                LocalTime poczatek = podajCzasy(scanner);
                LocalTime koniec = podajCzasy(scanner);
                show(kalendarz.filtrListBy(a -> (a.getCzasKonca().isBefore(koniec) || a.getCzasKonca().equals(koniec)) && (a.getCzasPoczatku().isAfter(poczatek) || a.getCzasPoczatku().equals(poczatek)),ktoryDzien(scanner)));
            }
            case 7 ->{
                LocalTime koniec = podajCzasy(scanner);
                String priorytet = podajPriorytet(scanner);
                show(kalendarz.filtrListBy(a -> a.getCzasPoczatku().isBefore(koniec) && a.getPriorytet().equalsIgnoreCase(priorytet) ,ktoryDzien(scanner)));
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
        Scanner sc2 = new Scanner(System.in);
        int dzien = ktoryDzien(scanner);
        String opis;
        System.out.println("Podaj opis spotkania: ");
        opis = sc2.nextLine();
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
        if(kalendarz.czyJestTakieSpotkanie(dzien, ktora))
            return ktora;
        return -1;
    }
    private static int ktoryDzien(Scanner scanner){
        int ktory;
        System.out.println("Podaj dzien: ");
        ktory = scanner.nextInt();
        return ktory - 1;
    }
}
