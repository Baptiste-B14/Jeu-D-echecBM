
public class Jeu {

    /**
     * Méthode principale du programme, elle permet l'appel de toutes les autres fonctions dans l'ordre correct de déroulement.
     *
     * Les pièces des joueurs sont stockées dans deux tableaux, un part couleur. Ainsi, en fonction du tableau considéré, on connait la couleur des pièces. Il est nécessaire de vérifier l'appel de paramètres,
     * car si le mauvais tableau est appelé, la couleur considérée sera la mauvaise. Par ailleurs, les pièces sont stockées selon un ordre précis : 0 = Roi, 1 =Dame, 2,3 = Fou, 4,5 = Cavalier, 6,7 = Tour
     * >7 = Pion. La variable indicePièce est donc essentielle, car en fonction de sa valeur, les fonctions de déplacement seront différentes, ect.
     *
     * @param aide le boolean qui active l'aide aux solutions d'échecs
     * @param blitz le boolean qui active le mode blitz
     */
    public static void jeu(boolean aide, boolean blitz){

        //Définitions des variables générales

        //Declaration et initialisation des tableaux de positionnement
        // tableau = {{Roi},{Dame},{Fou},{Fou},{Cavalier},{Cavalier},{Tour},{Tour},{Pion},{Pion},{Pion},{Pion},{Pion},{Pion},{Pion},{Pion}};
        int[][] tblanc = {{0,4},{0,3},{0,2},{0,5},{0,1},{0,6},{0,0},{0,7},{1,0},{1,1},{1,2},{1,3},{1,4},{1,5},{1,6},{1,7}};//Echiquier de démarrage de la partie. Toutes les parties doivent commencer avec lui
        int[][] tnoir = {{7,4},{7,3},{7,2},{7,5},{7,1},{7,6},{7,0},{7,7},{6,0},{6,1},{6,2},{6,3},{6,4},{6,5},{6,6},{6,7}};



        char[] pNoir =  {'p', 'p', 'p', 'p', 'p', 'p', 'p' ,'p'};//tableaux des promotions des pions. Par défaut, tout les pions n'ont aucune promotions.
        char[] pBlanc =  {'p', 'p', 'p', 'p', 'p', 'p', 'p' ,'p'};




        //définition de tableaux de test de certaines situation particulières


        //test de déplacement de pion, avec prise de pièce ennemie
        //int[][] tblanc = {{5,2},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{1,5},{2, 6},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};
        //int[][] tnoir = {{7,0},{0,1},{-1,-1},{-1,-1},{-1,-1},{3,5},{-1,-1},{-1,-1},{4,3},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};

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



        //fin de la définitions des tableaux particuliers


        int couleur; //blanc = 0; noir = 1;
        String sCouleur;//utile à l'affichage de la couleur uniquement
        boolean echecEtMat = false;//tant que false, le jeu continu
        int tour = 0;//compteur de tour, utile pour déterminer la couleur en début de tour
        int[][] tabJoueur, tabEnnemi;//tableaux temporaires, changent a chaque tour, afin de pouvoir les manipuler sans perdre l'information
        char[] promoJoueur, promoEnnemi;//tableau de promotion temporaire
        long chronoBlanc =0;//les chrono des joueurs
        long chronoNoir = 0;
        long chronoDepart = 0;
        boolean resultFonction;//stocke tout les résultats des fonctions, afin d'éviter le double appel de fonction lors des successions de do while/ if
        long tempsInterne = 0;//le temps du tour

        int indicePiece;//tout les tableaux contenant les positions des pièces sont organisés selon un ordre qui indique quelle est la pièce en fonction de son indice. L'indice est essentiel à l'appel de fonction.
        int[] position = new int[2];


        //fin définition des variables générales

        Affichage.printBoard(tblanc, tnoir, pBlanc, pNoir);

        do {//premier do while qui vérifie qu'il n'y a pas echec et mat, sinon le jeu est arrété.
            if (blitz) {//lance le chrono si le mode blitz est activé
                chronoDepart = Chrono.start();
            }

            int[][] tabSolutions = {{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}};//tableau des solutions en cas d'echec. Il ne sera affiché que si le mode aide est activé. Il sera tout de même remplie
            couleur = tour % 2;//determine la couleur en fonction du tour, le tout commençant à 0, par les blancs. Ainsi tour pair = blanc, impair = noir
            if (couleur == 1) sCouleur = "noir";
            else sCouleur = "blanc";

            System.out.println("Tour n°" + (tour + 1) + " Joueur : " + sCouleur);//+1 pour affichage correct

            do {
                tabJoueur = new int[16][2];//ces quatres tableaux correspondent à des tableaux temporaires, qui permettent de manipuler les bonnes valeurs, sans se soucier de la couleur,
                tabEnnemi = new int[16][2];//remplacé par Joueur et Ennemi, rendant ainsi plus facile la compréhension
                promoJoueur = new char[8];//Cette étape sert aussi à réinitialiser les tableaux temporaires, si on est en milieu de partie
                promoEnnemi = new char[8];


                //Joueur en cours.

                //Copie des tableaux des deux couleurs, afin de ne manipuler que le joueur en cours, et son ennemi, quelque soit la couleur
                //La copie s'effectue par des for, afin de copier les valeurs, et non le tableau
                if (couleur == 0) {
                    for (int i = 0; i < tblanc.length; i++)
                        for (int j = 0; j < tblanc[i].length; j++) {
                            tabJoueur[i][j] = tblanc[i][j];
                            tabEnnemi[i][j] = tnoir[i][j];
                        }
                    for (int i = 0; i < pBlanc.length; i++) {
                        promoJoueur[i] = pBlanc[i];
                        promoEnnemi[i] = pNoir[i];
                    }
                } else {
                    for (int i = 0; i < tnoir.length; i++)
                        for (int j = 0; j < tnoir[i].length; j++) {
                            tabJoueur[i][j] = tnoir[i][j];
                            tabEnnemi[i][j] = tblanc[i][j];
                        }
                    for (int i = 0; i < pNoir.length; i++) {
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

                            //On s'assur que la case n'est pas occupée par une pièce du joueur en cours
                            resultFonction = Fonction.caseDisponible(position[0], position[1], tabJoueur);
                            if (!resultFonction)
                                System.out.println("La case est déjà occupée par une de vos pièces");
                        } while (!resultFonction);
                        //s'assure que le schéma de déplacement correspond à la pièce saisie
                        resultFonction = Fonction.verificationDeplacementPossible(indicePiece, position[0], position[1], couleur, tabJoueur, tabEnnemi, promoJoueur);
                        if (!resultFonction)
                            System.out.println("Le déplacement ne correspond pas à la pièce choisie");
                    } while (!resultFonction);
                    resultFonction = Fonction.verificationDuChemin(indicePiece, position[0], position[1], tabJoueur, tabEnnemi, promoJoueur);
                    //s'assure que le chemin est libre selon le déplacement de la pièce. Sinon, la saisie est redemandée
                    if (!resultFonction)
                        System.out.println("Une pièce se trouve sur le chemin");
                } while (!resultFonction);

                //Le déplacement est approuvé pour la pièce, mais il faut vérifier que ce mouvement ne met pas en echec notre roi
                //l'échiquier est donc actualisé avec le déplacement. Cet échiquier temporaire est ainsi testé.
                Fonction.actualisationEchiquier(indicePiece, position[0], position[1], tabJoueur, tabEnnemi);
                resultFonction = Fonction.echecRoi(couleur, tabJoueur, tabEnnemi, promoEnnemi);
                if (resultFonction) System.out.println("Votre roi sera en echec avec ce déplacement");
            } while (resultFonction);//Tant que echec, alors on doit choisir une position valide



            //Le déplacement est approuvé totalement. Il faut vérifier si c'est un pion qui a bougé, et si il a ainsi la possibilité d'être promu
            if (Fonction.promotionPossible(indicePiece, couleur, tabJoueur, promoJoueur))
                Fonction.promotion(indicePiece, promoJoueur);//la promotion est possible, il faut donc l'effectuer


            //Joueur en cours.
            //les tableaux temporaires sont donc définitifs, ils faut donc mettre a jour les originaux. Une simple copie suffit, car on remplace leur ancien contenu
            if (couleur == 0) {
                tblanc = tabJoueur;
                tnoir = tabEnnemi;
                pBlanc = promoJoueur;
            } else {
                tnoir = tabJoueur;
                tblanc = tabEnnemi;
                pNoir = promoJoueur;

            }
            Affichage.printBoard(tblanc, tnoir, pBlanc, pNoir);
            //affichage()

            if (Fonction.echecRoi(couleur, tabEnnemi, tabJoueur, promoJoueur))// on teste si il y a un echec, afin de vérifier si un echec et mat du  roi adverse est possible
                echecEtMat = Fonction.echecEtMat(couleur, tblanc, tnoir, pBlanc, pNoir, promoEnnemi, aide, tabSolutions);


            Affichage.affichageSolutions(aide, tabSolutions);//l'affichage des solutions se fait en interne si l'aide est activée

            if (blitz){//si le blitz est activé, alors on affiche calcul les temps, puis on les affiche
                tempsInterne = Chrono.pause(chronoDepart);

                if (tour % 2 == 0) {
                    chronoBlanc += tempsInterne;
                    Chrono.convert(chronoBlanc);

                } else{
                    chronoNoir += tempsInterne;
                    Chrono.convert(chronoNoir);
                }

                if(chronoNoir > 1.2 * Math.pow(10, 6) || chronoBlanc> 1.2*Math.pow(10,6)){
                    echecEtMat= true;
                    System.out.println("" + Math.pow(1.2,6));
                    System.out.println("Vous avez perdu, le temps est écoulé");
                }

            }
            tour++;//dernière étape, incrémenter le tour, afin de changer de couleur.
        }while(!echecEtMat);
        //echec et mat, sinon encore dans le do while

    }//menu()
}//class menu
