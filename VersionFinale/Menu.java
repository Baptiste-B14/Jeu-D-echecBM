import java.util.Scanner;


public class Menu {
    public static void menu(){

        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        //Declaration et initialisation des tableaux de positionnement

        int[][] tblanc = {{0,4},{0,3},{0,2},{0,5},{0,1},{0,6},{0,0},{0,7},{1,0},{1,1},{1,2},{1,3},{1,4},{1,5},{1,6},{1,7}};
        int[][] tnoir = {{7,4},{7,3},{7,2},{7,5},{7,1},{7,6},{7,0},{7,7},{6,0},{6,1},{6,2},{6,3},{6,4},{6,5},{6,6},{6,7}};

        //int[][] tblanc = {{0,7},{-1,-3},{2,5},{-1,-5},{7,4},{-1,-6},{0,1},{3,0},{3,3},{-1,-1},{-1,-2},{-1,-3},{-1,-4},{-1,-5},{-1,-6},{-1,-7}};
        //int[][] tnoir = {{5,1},{-7,-3},{-7,-2},{-7,-5},{-7,-1},{-7,-6},{-7,-1},{-7,-7},{-6, -1},{-6,-1},{-6,-2},{-6,-3},{-6,-4},{-6,-5},{-6,-6},{-6,-7}};

        int couleur; //blanc =0; noir = 1
        String sCouleur;
        boolean echecEtMat = false;
        int tour = 0;
        int[][] tabJoueur;
        int[][] tabEnnemi;

        int piece;
        int[] position = new int[2];

        Affichage.printBoard(tblanc, tnoir);

        do {


            couleur = tour % 2;
            if (couleur == 1) sCouleur = "noir";
            else sCouleur = "blanc";

            System.out.println("Tour n°" + (tour+1) + " Joueur : " + sCouleur);

            do {
                tabJoueur = new int[16][2];
                tabEnnemi = new int[16][2];

                //Joueur en cours.
                if (couleur == 0) {//copie par for

                    for (int i = 0; i < tblanc.length; i++) {
                        for (int j = 0; j < tblanc[i].length; j++) {
                            tabJoueur[i][j] = tblanc[i][j];
                            tabEnnemi[i][j] = tnoir[i][j];
                        }
                    }

                } else {
                    for (int i = 0; i < tnoir.length; i++) {
                        for (int j = 0; j < tnoir[i].length; j++) {
                            tabJoueur[i][j] = tnoir[i][j];
                            tabEnnemi[i][j] = tblanc[i][j];
                        }
                    }

                }

                do {


                    do {

                        do {
                            //Demande de selection du pion.
                            //Saisie forcée d'un pion valide
                            piece = Fonction.demandeSaisie(tabJoueur);

                            //Demande la future position de la pièce.

                            position = Fonction.demandePosition();


                            //Pas d'echec et mat (testé à la fin du tour précédent. Comme la position demandé ne met pas en echec
                            //on peut vérifier que la position est correcte.


                        } while (!Fonction.caseDisponible(position[0], position[1], tabJoueur));
                        System.out.println("case Dispo");


                    } while (!Fonction.verificationDeplacementPossible(tabJoueur, tabEnnemi, piece, position[0], position[1], couleur));

                    System.out.println("schema de deplacement valide");
                } while (!Fonction.verificationDuChemin(piece, position[0], position[1], tabJoueur, tabEnnemi));
                System.out.println("pas d'obstacle");
                Fonction.actualisationEchiquier(position[0], position[1], piece, tabJoueur, tabEnnemi);


            } while (Fonction.echecRoi(couleur,  tabJoueur,tabEnnemi));//Tant que echec, alors on doit choisir une position valide


            //Joueur en cours.
            if (couleur == 0) {
                tblanc = tabJoueur;
                tnoir = tabEnnemi;
            } else {
                tnoir = tabJoueur;
                tblanc = tabEnnemi;
            }
            Affichage.printBoard(tblanc, tnoir);

            //affichage()


            if (Fonction.echecRoi(couleur, tabEnnemi, tabJoueur)) {//Ne pas modifier l'ordre des tab param. On cherche le roi ennemi (fin du tour)
                echecEtMat = Fonction.echecEtMat(tblanc, tnoir, couleur, tour);
            }


            tour++;

        }while(!echecEtMat);



    }//menu()


}//class menu