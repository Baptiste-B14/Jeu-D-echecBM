import java.util.Scanner;

public class Mode {

    public static void mode(){
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        int choix;
        boolean blitz;
        String choixBlitz;
        do{
            System.out.println("Choisir votre niveau de difficultée : 1 | 2");
            choix= sc.nextInt();
        }while(choix != 1 && choix != 2);

        do{
            System.out.println("Mode de jeu blitz ? Oui | Non  ( o, n )");
            choixBlitz = sc.nextLine().toLowerCase();
        }while(!choixBlitz.equals("o") && !choixBlitz.equals("n"));


        if(choixBlitz.equals("o"))blitz = true;
        else blitz = false;

        Menu.menu(choix, blitz);
    }



}
