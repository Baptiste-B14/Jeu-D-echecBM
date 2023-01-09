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
            System.out.println("Mode de jeu blitz ? ( Oui/Non )");
            choixBlitz = sc.nextLine().toLowerCase();
        }while(!choixBlitz.equals("oui") && !choixBlitz.equals("non"));


        if(choixBlitz.equals("oui"))blitz = true;
        else blitz = false;


        System.out.println("Pour sélectionner une case, veuillez respecter le format LettreChiffre (B5) tout au long de la partie.");
        Menu.menu(aide, blitz);
    }



}
