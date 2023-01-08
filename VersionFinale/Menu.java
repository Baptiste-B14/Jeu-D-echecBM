
public class Menu {
    public static void menu(boolean aide, boolean blitz){

        //Declaration et initialisation des tableaux de positionnement
        // tableau = {{Roi},{Dame},{Fou},{Fou},{Cavalier},{Cavalier},{Tour},{Tour},{Pion},{Pion},{Pion},{Pion},{Pion},{Pion},{Pion},{Pion}};
        //int[][] tblanc = {{0,4},{0,3},{0,2},{0,5},{0,1},{0,6},{0,0},{0,7},{1,0},{1,1},{1,2},{1,3},{1,4},{1,5},{1,6},{1,7}};
        //int[][] tnoir = {{7,4},{7,3},{7,2},{7,5},{7,1},{7,6},{7,0},{7,7},{6,0},{6,1},{6,2},{6,3},{6,4},{6,5},{6,6},{6,7}};

                                 //1 3                                                                      //pion en dame
        //int[][] tblanc = {{1,7},{-6,-3},{-2,-5},{-1,-5},{-7,-4},{-1,-6},{-1,-1},{-3,-1},{-3,-3},{-1,-1},{-1,-2},{6,3},{-1,-4},{-1,-5},{-1,-6},{-1,-7}};
        //int[][] tnoir = {{5,1},{-7,-3},{-7,-2},{-7,-5},{-7,-1},{-7,-6},{-7,-1},{-7,-7},{-6, -1},{-6,-1},{-6,-2},{2,4},{-6,-4},{-6,-5},{-6,-6},{-6,-7}};

        char[] pNoir =  {'p', 'p', 'p', 'p', 'p', 'p', 'p' ,'p'};
        char[] pBlanc =  {'p', 'p', 'p', 'p', 'p', 'p', 'p' ,'p'};

        int[][] tblanc = {{5,2},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{1,5},{2, 6},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};
        int[][] tnoir = {{7,0},{0,1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{4,3},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};

        //int[][] tblanc = {{0,7},{-1,-3},{2,5},{-1,-5},{7,4},{-1,-6},{0,1},{3,0},{3,3},{-1,-1},{-1,-2},{-1,-3},{-1,-4},{-1,-5},{-1,-6},{-1,-7}};
        //int[][] tnoir = {{5,1},{-7,-3},{-7,-2},{-7,-5},{-7,-1},{-7,-6},{-7,-1},{-7,-7},{-6, -1},{-6,-1},{-6,-2},{-6,-3},{-6,-4},{-6,-5},{-6,-6},{-6,-7}};

        // Le pion adverse ne peut pas prendre en arrière
        //int[][] tblanc = {{5,2},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};
        //int[][] tnoir = {{7,0},{0,1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{4,3},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};

        // Le Roi peut prendre un cavalier
        //int[][] tblanc = {{5,3},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};
        //int[][] tnoir = {{7,0},{-1,-1},{-1,-1},{-1,-1},{6,2},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};

        // Une Tour peut prendre le Roi, mais un pion adverse empêche la prise. De plus le joueur est déjà mis en échec : saisie forcée
        //int[][] tblanc = {{1,6},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{0,5},{-1,-1},{3,6},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};
        //int[][] tnoir = {{7,5},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{5,6},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};

        // Une Tour et un Fou blancs peuvent mettre en échec le Roi mais il y a deux pions qui les en empêchent, possibilité de prise : mini-jeu
        //int[][] tblanc = {{0,7},{-1,-1},{4,4},{-1,-1},{-1,-1},{-1,-1},{3,7},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};
        //int[][] tnoir = {{7,7},{-1,-1},{5,5},{4,5},{7,6},{-1,-1},{-1,-1},{-1,-1},{6,7},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};

        // Impossibilité d'avancer un pion vers l'avant, possible échec mais un pion bloque le chemin, lui-même peut prendre une dame adverse.
        //int[][] tblanc = {{0,3},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{1,2},{2,2},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};
        //int[][] tnoir = {{0,0},{2,1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};


        int couleur; //blanc = 0; noir = 1;
        String sCouleur;
        boolean echecEtMat = false;
        int tour = 0;
        int[][] tabJoueur, tabEnnemi;
        char[] promoJoueur, promoEnnemi;
        long chronoBlanc;
        long chronoNoir;

        int indicePiece;
        int[] position = new int[2];

        Affichage.printBoard(tblanc, tnoir, pBlanc, pNoir);

        do {
            int[][] tabSolutions = {{-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}};
            couleur = tour % 2;
            if (couleur == 1) sCouleur = "noir";
            else sCouleur = "blanc";

            System.out.println("Tour n°" + (tour+1) + " Joueur : " + sCouleur);

            do {
                tabJoueur = new int[16][2];
                tabEnnemi = new int[16][2];
                promoJoueur = new char[8];
                promoEnnemi = new char[8];


                //Joueur en cours.
                if (couleur == 0) {
                    for (int i = 0; i < tblanc.length; i++)
                        for (int j = 0; j < tblanc[i].length; j++) {
                            tabJoueur[i][j] = tblanc[i][j];
                            tabEnnemi[i][j] = tnoir[i][j];
                        }
                    for(int i = 0; i<pBlanc.length; i++){
                        promoJoueur[i] = pBlanc[i];
                        promoEnnemi[i] = pNoir[i];
                    }
                } else {
                    for (int i = 0; i < tnoir.length; i++)
                        for (int j = 0; j < tnoir[i].length; j++) {
                            tabJoueur[i][j] = tnoir[i][j];
                            tabEnnemi[i][j] = tblanc[i][j];
                        }
                    for(int i = 0; i<pNoir.length; i++){
                        promoJoueur[i] = pNoir[i];
                        promoEnnemi[i] = pBlanc[i];
                    }
                }

                do {
                    do {
                        do {
                            //Demande de selection du pion.
                            //Saisie forcée d'un pion valide
                            indicePiece = Fonction.demandeSaisie(tabJoueur);

                            //Demande la future position de la pièce.

                            position = Fonction.demandePosition();

                            //Pas d'echec et mat (testé à la fin du tour précédent. Comme la position demandée ne met pas en echec
                            //on peut vérifier que la position est correcte.
                            if(!Fonction.caseDisponible(position[0], position[1], tabJoueur))
                                System.out.println("La case est déjà occupée par une de vos pièces");
                        } while (!Fonction.caseDisponible(position[0], position[1], tabJoueur));

                        if(!Fonction.verificationDeplacementPossible(indicePiece, position[0], position[1], couleur, tabJoueur, tabEnnemi, promoJoueur))
                            System.out.println("Le déplacement ne correspond pas à la pièce choisit");
                    } while (!Fonction.verificationDeplacementPossible(indicePiece, position[0], position[1], couleur, tabJoueur, tabEnnemi, promoJoueur));
                    if(!Fonction.verificationDuChemin(indicePiece, position[0], position[1], tabJoueur, tabEnnemi, promoJoueur))
                        System.out.println("Une pièce se trouve sur le chemin");
                } while (!Fonction.verificationDuChemin(indicePiece, position[0], position[1], tabJoueur, tabEnnemi, promoJoueur));
                Fonction.actualisationEchiquier(indicePiece, position[0], position[1], tabJoueur, tabEnnemi);
                if(Fonction.echecRoi(couleur, tabJoueur, tabEnnemi, promoEnnemi)) System.out.println("Votre roi sera en echec avec ce déplacement");
            } while (Fonction.echecRoi(couleur, tabJoueur, tabEnnemi, promoEnnemi));//Tant que echec, alors on doit choisir une position valide

            if(Fonction.promotionPossible(indicePiece, couleur, tabJoueur, promoJoueur))Fonction.promotion(indicePiece, promoJoueur);


            //Joueur en cours.
            if (couleur == 0) {
                tblanc = tabJoueur;
                tnoir = tabEnnemi;
                pBlanc = promoJoueur;
            } else {
                tnoir = tabJoueur;
                tblanc = tabEnnemi;
                pNoir = promoJoueur;

            }
            Affichage.printBoard(tblanc, tnoir, pBlanc,pNoir);
            //affichage()

            if (Fonction.echecRoi(couleur, tabEnnemi, tabJoueur, promoJoueur))
                echecEtMat = Fonction.echecEtMat(couleur, tblanc, tnoir, pBlanc, pNoir, promoEnnemi, aide, tabSolutions);


            Affichage.affichageSolutions(aide, tabSolutions);
            tour++;

        }while(!echecEtMat);
    }//menu()
}//class menu
