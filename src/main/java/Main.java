import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner sc=new Scanner(System.in);
    static Film_Service filmService=new Film_Implementation();
    public static void main(String[] args) {

        System.out.println("1:INSERT FILM DETAILS \n 2:UPDATE FILM YEAR \n 3:DISPLAY FILMS WHOSE RATINGS ARE IMPRESSIVE \n 4:DISPLAY 5 FILMS WHO'S CHANCES ARE MAX TO NOMINATE FOR OSCAR AWARD");
        int choice=sc.nextInt();

        switch (choice){
            case 1:
                insertFilmData();
                break;
            case 2:
                updateFilmYear();
                break;
            case 3:
                displayFilmByAvg();
                break;
            case 4:
                displayFilmsForOscar();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.err.println("INVALID CHOICE");
        }
        main(args);
    }

    public static void displayFilmsForOscar() {
        List<Films> filmsList=filmService.displayFilmsForOscar();
        for(Films f:filmsList){
            System.out.println(f.getFilmName());
        }

    }

    public static void displayFilmByAvg() {
        List<Films> filmsList=filmService.displayFilmByAvg();
        for (Films f: filmsList){
            System.out.println(f.getFilmName());
        }
    }

    public static void updateFilmYear() {
        System.out.println("Enter Film Name");
        String film_name=sc.next();
        System.out.println("Enter Updated Film YEar");
        int film_year=sc.nextInt();
        Films f=new Films(0,film_name,film_year,"",0);
        int n=filmService.updateFilmYear(f);
        System.out.println(n+ "Rows Updated");
    }

    public static void insertFilmData() {
        System.out.println("Enter Film Name");
        String film_name=sc.nextLine();
        film_name=sc.nextLine();
        System.out.println("Enter Film Year");
        int film_year=sc.nextInt();
        System.out.println("Enter Film Language");
        String film_lang=sc.next();
        System.out.println("Enter Film Rating out off 5");
        float film_rating=sc.nextFloat();
        Films f=new Films(0,film_name,film_year,film_lang,film_rating);
        int n=filmService.insertFilmDetails(f);
        System.out.println(n+" Rows Added");
    }
}