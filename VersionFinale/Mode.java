import java.util.Scanner;

public class Mode {

    /**
     * Méthode qui permet d'activer certaines fonctions du jeu.
     * Elle utilise la saisie forcée afin de pouvoir activer ou non les fonctions. En fonction des résultats, le jeu sera créé.
     */
    public static void mode(){
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        boolean blitz = false;
        String choixBlitz;
        boolean aide=false;
        String reponse;
        System.out.println("Si vous êtes novices, une aide est possible souhaitez-vous en bénéficier ? [Oui/Non]");

        do{//saisie forcée de oui ou non (la casse est ignorée grace au .toLowerCase())
            reponse = sc.nextLine().toLowerCase();
        }while(!reponse.equals("oui") && !reponse.equals("non"));

        if (reponse.equals("oui"))//active l'aide aux solutions
            aide=true;

        do{//saisie forcée pour le mode blitz
            System.out.println("Mode de jeu blitz ? ( Oui/Non )");
            choixBlitz = sc.nextLine().toLowerCase();
        }while(!choixBlitz.equals("oui") && !choixBlitz.equals("non"));

        if(choixBlitz.equals("oui"))blitz = true;//active le mode blitz

        System.out.println("Pour sélectionner une case, veuillez respecter le format LettreChiffre (B5) tout au long de la partie.");//indication pour le format de saisie des cases.
        Jeu.jeu(aide, blitz);//création du jeu
    }



}
