import java.time.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Kalendarz kalendarz = new Kalendarz(7);//wybor ile dni planujemy
        System.out.println("kalendarz");

        ArrayList<Spotkanie> dziennik = kalendarz.getSpotkanie(1);//dziennik = arraylista majaca obiekty Spotkania z danego dnia
        do{
            System.out.println("1)pokazanie\n2)stworzenie\n3)usuniecie\n4)priorytet");
            int wybor = scanner.nextInt();
            if(wybor == 5){
                System.out.println("koniec programu");
                break;
            }
            menu(scanner,wybor,dziennik,kalendarz);
        }while (true);
    }
    public static void show(Kalendarz kalendarz, Scanner scanner){
        ArrayList<Spotkanie> dziennik = kalendarz.getSpotkanie(ktoryDzien(scanner));//array listy
        for(Spotkanie wartosci : dziennik){//przechodzenie przez arrayListy obiektem Spotkanie
            System.out.println(wartosci.naString());
        }
    }
    public static void menu(Scanner scanner,int wybor, ArrayList<Spotkanie> dziennik, Kalendarz kalendarz){
        switch (wybor){
            case 1 -> show(kalendarz,scanner);
            case 2 -> {
                int dzien = ktoryDzien(scanner);// potrzebujemy wiedziec do jakiego dnia dodac
                Spotkanie spotkanie2 = stworzSpotkanie(scanner);//tworzymy obiekt Spotkanie z danymi od usera
                kalendarz.addSpotkanie(dzien,spotkanie2);//dodajemy obiekt do arraylisty
            }
            case 3->{
                int dzien = ktoryDzien(scanner);
                int ktore = ktoreSpotkanie(scanner,dziennik);//musimy wiedziec ktory index z arraylisty z dnia [[spotkanie1,spotkanie2...],[spotkanie1...]]
                if(ktore==-1){System.out.println("nie ma takiego spotkania");break;}
                kalendarz.removeSpotkanie(dzien, dziennik.get(ktore));//usuwamy znajac dzien i index z listy dnia
            }
            case 4 -> ktoryPriorytet(scanner, kalendarz);
        }
    }
    public static Spotkanie stworzSpotkanie(Scanner scanner){
        String opis;
        System.out.println("Podaj opis spotkania: ");
        opis = scanner.next();
        System.out.println("Podaj czas zaczecia pozniejszy niz 08:00: ");
        LocalTime czas3 = LocalTime.parse(scanner.next());
        System.out.println("Podaj czas zakonczenia: ");
        LocalTime czas4 = LocalTime.parse(scanner.next());
        System.out.println("Podaj priorytet High,Medium,Low: ");
        String priorytet = scanner.next();
        Spotkanie stworzoneSpotkanie = new Spotkanie(opis,czas3,czas4,priorytet);
        if(stworzoneSpotkanie.czyWczesnie()){
            System.out.println("za wczesnie!");
            return stworzSpotkanie(scanner);//to stworzy znowu obiekt wiec poprzedni starci ref i bedzie pod garbage collectorem
        }
        return  stworzoneSpotkanie;
    }
    public static int ktoreSpotkanie(Scanner scanner, ArrayList<Spotkanie> dziennik){
        int ktora;
        System.out.println("podaj ktore spotkanie chcesz usunac");
        ktora = scanner.nextInt();
        if (ktora>dziennik.size())
            return -1;
        return ktora -1;
    }
    public static int ktoryDzien(Scanner scanner){
        int ktory;
        System.out.println("Podaj dzien: ");
        ktory = scanner.nextInt();
        return ktory-1;
    }
    public static void ktoryPriorytet(Scanner scanner, Kalendarz kalendarz){
        ArrayList<Spotkanie> dziennik = kalendarz.getSpotkanie(ktoryDzien(scanner));
        System.out.println("podaj jaki priorytet High, Medium, Low");
        String jaki = scanner.next();
        int i = 0;
        for(Spotkanie wartosci : dziennik){//sprawdzamy [[spotaknie1,spotkanie2,...]]
            i++;
            if(wartosci.getPriorytet().equals(jaki)){//wyjmujemy priorytet jako string z spotkanie[1...] i porowannie
                System.out.println(dziennik.get(i-1).naString());//jak prownanie ok to printujemy cale spotkanie
            }
        }
    }
}
