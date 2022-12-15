import java.util.Scanner;

public class Fonction {


    public static int demandeSaisie( int[][] tabJoueur){
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        int indicePiece = -1;
        boolean caseNonVide = false;



        do {

            String CaseEchiquier = "";
            do {

                System.out.println("Saisir la position de la pièce à déplacer: (format Lettre Chiffre: B5)");
                CaseEchiquier = sc.nextLine().toUpperCase();

            } while (CaseEchiquier.length() != 2 || (CaseEchiquier.charAt(0) < 65 || CaseEchiquier.charAt(0) > 72) || (CaseEchiquier.charAt(1) < 49 || CaseEchiquier.charAt(1) > 56));


            for (int i = 0; i < tabJoueur.length; i++) {
                if ((tabJoueur[i][0] == CaseEchiquier.charAt(0) - 65) && tabJoueur[i][1] == CaseEchiquier.charAt(1) - 49) {
                    indicePiece = i;
                    caseNonVide = true;
                }


            }
            if(!caseNonVide) System.out.println("La case est vide ou contient une pièce qui n'est pas a vous");

        }while(!caseNonVide);
        System.out.println("Pièce selectionnée.");
        return indicePiece;

    }

    public static int[] demandePosition(){
        Scanner sc = new Scanner(System.in).useDelimiter("\n");

        int[] position = new int[2];



        String CaseEchiquier = "";
        do {

            System.out.println("Saisir une position : (format Lettre Chiffre: B5)");
            CaseEchiquier = sc.nextLine().toUpperCase();

        }while(CaseEchiquier.length() != 2 || (CaseEchiquier.charAt(0) < 65 || CaseEchiquier.charAt(0) > 72) || (CaseEchiquier.charAt(1) < 49 || CaseEchiquier.charAt(1) > 56));

        position[0] = CaseEchiquier.charAt(0) - 65;
        position[1] = CaseEchiquier.charAt(1) - 49;


        System.out.println("Case selectionnée : " + position[0]+ " " + position[1]);
        return position;
    }


    public static boolean caseDisponible (int i, int j, int[][] tabJoueur){

            for (int k = 0; k < tabJoueur.length; k++) {
                if (tabJoueur[k][0] == i && tabJoueur[k][1] == j) {
                    System.out.println("La case est déjà occupée par une de vos pièces");
                    return false;
                }
            }

        return true;

    }


    public static boolean verificationDeplacementPossible(int tabJoueur[][],int tabEnnemi[][], int indice, int i, int j, int couleur){
        boolean possible = false;



        if(indice == 0) possible = deplacementRoi(indice, i, j, tabJoueur);


        else if(indice == 1) possible = deplacementReine(indice, i, j, tabJoueur);

        else if(indice == 2 || indice == 3) possible = deplacementFou(indice, i, j, tabJoueur);

        else if(indice == 4 || indice == 5) possible = deplacementCavalier(indice, i, j, tabJoueur);

        else if(indice == 6 || indice == 7) possible = deplacementTour(indice, i, j, tabJoueur);

        else if(indice >7 ) possible = deplacementPion(indice, i, j, couleur, tabJoueur, tabEnnemi);

        if(possible==false)System.out.println("Ce déplacement ne correspond pas à la pièce sélectionnée");

        return possible;
    }

    public static boolean deplacementPion(int indicePiece, int i, int j, int couleur, int[][] tabJoueur, int[][] tabEnnemi){
        boolean possible = false;
        if (couleur==0){
            // Voir quand le pion essaie d'aller une ligne plus loin
            if (tabJoueur[indicePiece][0]+1==i) {
                // Voir s'il y a une pièce noire sur la case[x+1][y]
                if (tabJoueur[indicePiece][1] == j) {
                    if (caseDisponible(i, j, tabEnnemi))
                        possible= true;
                }
                // Voir s'il y a une pièce noire sur la case[x+1][y-1] ou case[x+1][y+1]
                else if (j ==tabJoueur[indicePiece][1] - 1 || j== tabJoueur[indicePiece][1]+1) {
                    if (caseDisponible(i, j, tabJoueur))
                        possible= true;
                }
            }
            // Voir quand le pion est à l'origine et qu'il veut aller deux cases plus loin case[x+2][y]
            else if (tabJoueur[indicePiece][0] + 2 == i) {
                if (tabJoueur[indicePiece][1]== j) {
                    if (caseDisponible(i - 1, j, tabEnnemi) && caseDisponible(i, j, tabEnnemi))
                        possible = true;
                }
            }
        }
        else if(couleur==1){
            // Voir quand le pion essaie d'aller une ligne plus loin
            if (tabJoueur[indicePiece][0]-1==i) {
                // Voir s'il y a une pièce blanche sur la case[x-1][y]
                if (tabJoueur[indicePiece][1] == j) {
                    if (caseDisponible(i, j, tabEnnemi))
                        possible= true;
                }
                // Voir s'il y a une pièce blanche (adverse) sur la case[x-1][y-1] ou sur la case[x-1][y+1]
                else if (j == tabJoueur[indicePiece][1] - 1 || j== tabJoueur[indicePiece][1] + 1) {
                    if (caseDisponible(i, j, tabJoueur))
                        possible= true;
                }
            }
            // Voir quand le pion est à l'origine et qu'il veut aller deux cases plus loin case[x-2][y]
            else if (tabJoueur[indicePiece][0] - 2 == i) {
                if (tabJoueur[indicePiece][1]== j) {
                    if (caseDisponible(i + 1, j, tabEnnemi) && caseDisponible(i, j, tabEnnemi))
                        possible = true;
                }
            }
        }
        return possible;
    }


    public static boolean deplacementTour( int indice, int i, int j,int tabJoueur[][] ){  //indice = position actuelle, i = futur x, j = futur y
        int x = tabJoueur[indice][0];
        int y = tabJoueur[indice][1];

        return ((i!=x && j== y) || (i==x && j!=y));    // (i!=x && j== y) --> deplacement horizontal    (i==x && j!=y) --> deplacement vertical
    }

    public static boolean deplacementFou(int indice, int i, int j,int tabJoueur[][]){
        int x = tabJoueur[indice][0];
        int y = tabJoueur[indice][1];

        return ((Math.abs(x-i)) == (Math.abs(y-j)));  // le deplacement horizontal et vertical doivent avoir la meme valeur (diagonal).
        // afin de traiter tout les cas, valeur absolue evite d'avoir des valeur négatives i et j sup a x et y (deplacement vers le bas)é


    }

    public static boolean deplacementReine(int indice, int i, int j,int tabJoueur[][]){ // combinaison d'un deplacement de tour ou de fou
        return (deplacementFou( indice, i,j,tabJoueur ) || deplacementTour( indice, i, j, tabJoueur));
    }

    public static boolean deplacementCavalier(int indice, int i, int j,int tabJoueur[][]){
        int x = tabJoueur[indice][0];
        int y = tabJoueur[indice][1];

        return((Math.abs(x- i)==1 && Math.abs(y-j)==2) || (Math.abs(x- i)==2 && Math.abs(y-j)==1));  // la valeur absolue permet de s'occuper de la symetrie, quelque soit la direction du cavalier
        //une fois éliminée la symetrie selon x et y, il y a deux possibilité de position
        // x+1 et y+2 ou x+2 et y+1;
    }

    public static boolean deplacementRoi(int indice, int i, int j,int tabJoueur[][]){
        int x = tabJoueur[indice][0];
        int y = tabJoueur[indice][1];



        return ((Math.abs(y-j) == 1 && Math.abs(x-i) ==0) || (Math.abs(y-j) == 0 && Math.abs(x-i) ==1) || (Math.abs(y-j) == 1 && Math.abs(x-i) ==1));
    }


    public static boolean verificationDuChemin (int indicePiece, int i, int j, int[][] tabJoueur, int[][]tabEnnemi){
        boolean possible=false;
        if(indicePiece==1) possible = (verificationDuCheminDame(indicePiece, i, j, tabJoueur, tabEnnemi));

        else if(indicePiece == 2 || indicePiece==3) possible = verificationDuCheminFou (indicePiece, i, j, tabJoueur, tabEnnemi);


        else if(indicePiece==6 || indicePiece==7) possible = verificationDuCheminTour (indicePiece, i, j, tabJoueur, tabEnnemi);

        else possible = true;

        if(!possible)
            System.out.println("La case est inatteignable");

        return possible;
    }

    public static boolean verificationDuCheminTour(int indice, int i, int j,  int [][]tabJoueur, int [][]tabEnnemi){
        int x, y;
        x = tabJoueur[indice][0];
        y = tabJoueur[indice][1];


        if(x-i > 0){  // meme ligne, changement de colonne vers la gauche
            for(int k=1; k < Math.abs(x-i); k++){
                if(!caseDisponible(x-k, y, tabJoueur) || !caseDisponible(x-k, y, tabEnnemi)){
                    return false;
                }

            }
        }

        if(x-i < 0){  // meme ligne changement de colonne vers la droite
            for(int k=1; k < Math.abs(x-i); k++){
                if(!caseDisponible(x+k, y,tabJoueur)|| !caseDisponible(x+k, y, tabEnnemi)){
                    return false;
                }

            }
        }

        if(y-j < 0){ // meme colonne changement de ligne vers le bas
            for(int k=1; k < Math.abs(y-j); k++){
                if(!caseDisponible(x, y+k, tabJoueur)|| !caseDisponible(x, y+k, tabEnnemi)){
                    return false;
                }

            }
        }

        if(y-j > 0){ // meme colonne changement de ligne vers le haut
            for(int k=1; k < Math.abs(y-j); k++){
                if(!caseDisponible(x, y-k, tabJoueur)|| !caseDisponible(x, y-k, tabEnnemi)){
                    return false;
                }

            }
        }
        return true;

    }

    public static boolean verificationDuCheminDame(int indice, int i, int j, int[][] tabJoueur, int[][]tabEnnemi){
        return (verificationDuCheminTour(indice, i, j, tabJoueur, tabEnnemi) || verificationDuCheminFou(indice, i, j, tabJoueur, tabEnnemi));
    }

    public static boolean verificationDuCheminFou (int indicePiece, int i, int j, int[][]tabJoueur, int [][]tabEnnemi){
        int x, y;

        x=tabJoueur[indicePiece][0];
        y=tabJoueur[indicePiece][1];


        //Partie en haut
        if (j > y){

            if (i > x) {//Partie en haut à droite
                for (int k = 1; x + k < i; k++)
                    if (!caseDisponible(x + k, y + k, tabJoueur) || !caseDisponible(x + k, y + k, tabEnnemi))
                        return false;

            }else//Partie en haut à gauche
                for (int k = 1; x - k > i; k++)
                    if (!caseDisponible(x - k, y + k, tabJoueur) || !caseDisponible(x - k, y + k, tabEnnemi))
                        return false;

        }else//Partie en bas

            if (i > x){//Partie en bas à droite
                for (int k = 1; x + k < i; k++) {
                    if (!caseDisponible(x + k, y - k, tabJoueur)  || !caseDisponible(x + k, y - k, tabEnnemi))
                        return false;
                }

            }else {//Partie en bas à gauche
                for (int k = 1; x - k > i; k++) {
                    if (!caseDisponible(x - k, y - k, tabJoueur) || !caseDisponible(x - k, y - k, tabEnnemi))
                        return false;
                }

            }
        return true;

    }

    public static void actualisationEchiquier(int i , int j, int indice,int couleur, int[][] noir, int[][]blanc){
        int x, y;
        int [][] tabCouleur, tabEnnemi;

        if(couleur ==1){
            x = noir[indice][0];
            y = noir[indice][1];
            tabCouleur = noir;
            tabEnnemi = blanc;
        }else {
            x = blanc[indice][0];
            y = blanc[indice][1];
            tabCouleur = blanc;
            tabEnnemi = noir ;
        }


        for(int parcours = 0; parcours<tabEnnemi.length; parcours++){
            if((tabEnnemi[parcours][0] == i) && (tabEnnemi[parcours][1] == j)){
                tabEnnemi[parcours][0] = -1;
                tabEnnemi[parcours][1] = -1;
            }
        }
        tabCouleur[indice][0] = i;
        tabCouleur[indice][1] = j;

    }


    public static boolean echecRoi (int couleur, int[][] tabJoueur, int[][]tabEnnemi){
        int xRoi;
        int yRoi;

            xRoi = tabJoueur[0][0];
            yRoi = tabJoueur[0][1];
            for (int k=1; k<tabEnnemi.length ; k++){//k commence a 1 car on ne peut pas mettre en echec avec un roi
                if (tabEnnemi[k][0] >= 0){
                    if (verificationDeplacementPossible(tabEnnemi, tabJoueur,  k, xRoi, yRoi, couleur ) && verificationDuChemin (k, xRoi, yRoi, tabEnnemi, tabJoueur)) {

                        String sCouleur = "";
                        if (couleur == 1) sCouleur = "noir";
                        else sCouleur = "blanc";

                        System.out.println("Le roi de la couleur " + sCouleur + " est en echec.");
                        return true;
                    }
                }
            }


        return false;
    }


    public static boolean echecEtMat(int[][] tblanc, int [][] tnoir, int couleur, int tour){
        // On parcourt toutes les pièces du joueur ennemi pour voir si chacun de leur déplacement
        // ne permettrait pas d'éviter l'échec du roi
        int x, y;
        // On déclare les tableaux fictifs
        int[][] tabJoueur = new int[16][2];
        int[][] tabEnnemi = new int[16][2];
        //
        //
        //
        // On met les tableaux fictifs à la valeur des tableaux permanents

        //Joueur en cours.
        if (tour % 2 == 0) {//copie par for

            for(int i = 0; i<tblanc.length; i++){
                for(int j =0; j<tblanc[i].length; j++) {
                    tabJoueur[i][j] = tblanc[i][j];
                    tabEnnemi[i][j] = tnoir[i][j];
                }
            }

        }else{
            for(int i = 0; i<tblanc.length; i++){
                for(int j =0; j<tblanc[i].length; j++) {
                    tabJoueur[i][j] = tnoir[i][j];
                    tabEnnemi[i][j] = tblanc[i][j];
                }
            }

        }
        //
        //
        // On cherche à parcourir les 64 cases de l'échiquier
        for (int Case=0; Case<64; Case++){
            // On prend les coordonnées de chaque case
            x=Case%8;
            y=Case/8;
                // On parcourt les 16 pièces de l'ennemi
                for (int indicePiece=0; indicePiece<16; indicePiece++)
                    // On s'assure que la pièce manipulée n'est pas hors-jeu :
                    if (tabEnnemi[indicePiece][0]>=0)
                        // On vérifie si la pièce peut aller aux coordonnées de la case(x;y)
                        if (caseDisponible(x, y, tabEnnemi) && verificationDeplacementPossible(tabJoueur, tabEnnemi, indicePiece, x, y, couleur) && verificationDuChemin(indicePiece, x, y, tabJoueur, tabEnnemi)) {
                            actualisationEchiquier(x, y, indicePiece, couleur, tabJoueur, tabEnnemi);
                            if (!echecRoi(couleur, tabEnnemi,tabJoueur))//Ne pas modifier l'ordre des tab param. On cherche le roi ennemi (fin du tour)
                                return false;

                            // On ré-actualise les tableaux d'origine
                            // Pour ne pas enregistrer le déplacement fictif de la pièce


                            if (tour % 2 == 0) {//copie par for

                                for(int i = 0; i<tblanc.length; i++){
                                    for(int j =0; j<tblanc[i].length; j++) {
                                        tabJoueur[i][j] = tblanc[i][j];
                                        tabEnnemi[i][j] = tnoir[i][j];
                                    }
                                }

                            }else{
                                for(int i = 0; i<tblanc.length; i++){
                                    for(int j =0; j<tblanc[i].length; j++) {
                                        tabJoueur[i][j] = tnoir[i][j];
                                        tabEnnemi[i][j] = tblanc[i][j];
                                    }
                                }

                            }
                        }
        }

        System.out.println("Vous avez gagné !");
        return true;
    }



}
