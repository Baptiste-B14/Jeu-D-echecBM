import java.util.Scanner;

public class Mode {

    public static void mode(){
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        boolean blitz;
        String choixBlitz;
        boolean aide=false;
        String reponse;
        System.out.println("Si vous êtes novices, une aide est possible souhaitez-vous en bénéficier ? [Oui/Non]");

        do{
            reponse = sc.nextLine().toLowerCase();
        }while(!reponse.equals("oui") && !reponse.equals("non"));
        if (reponse.equals("oui"))
            aide=true;

        do{
            System.out.println("Mode de jeu blitz ? Oui | Non  ( o, n )");
            choixBlitz = sc.nextLine().toLowerCase();
        }while(!choixBlitz.equals("o") && !choixBlitz.equals("n"));


        if(choixBlitz.equals("o"))blitz = true;
        else blitz = false;

        Menu.menu(aide, blitz);
    }



}
