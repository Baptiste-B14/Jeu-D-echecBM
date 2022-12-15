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


    public static boolean verificationDeplacementPossible(int tab[][],int tabEnnemi[][], int indice, int i, int j, int couleur){
        boolean possible = false;



        if(indice == 0) possible = deplacementRoi(tab, indice, i, j);


        else if(indice == 1) possible = deplacementReine(tab, indice, i, j);

        else if(indice == 2 || indice == 3) possible = deplacementFou(tab, indice, i, j);

        else if(indice == 4 || indice == 5) possible = deplacementCavalier(tab, indice, i, j);

        else if(indice == 6 || indice == 7) possible = deplacementTour(tab, indice, i, j);

        else if(indice >7 ) possible = deplacementPion(tab, tabEnnemi, indice, i, j, couleur);

        else possible = false;

        return possible;
    }

    public static boolean deplacementPion( int[][] noir, int[][] blanc ,int indicePiece, int i, int j, int couleur){
        boolean possible = false;
        if (couleur==0){
            // Voir quand le pion essaie d'aller une ligne plus loin
            if (blanc[indicePiece][0]+1==i) {
                // Voir s'il y a une pièce noire sur la case[x+1][y]
                if (blanc[indicePiece][1] == j) {
                    for (int k = 0; k < noir.length; k++) {
                        if (noir[k][0] == blanc[indicePiece][0] + 1 && noir[k][1] == j)
                            possible = false;
                    }
                    possible = true;
                }
                // Voir s'il y a une pièce noire sur la case[x+1][y-1]
                else if (j == blanc[indicePiece][1] - 1) {
                    for (int k = 0; k < noir.length; k++) {
                        if (noir[k][0] == blanc[indicePiece][0] + 1 && noir[k][1] == j)
                            possible =  true;
                    }
                }
                // Voir s'il y a une pièce noire sur la case[x+1][y+1]
                else if (j == blanc[indicePiece][1] + 1) {
                    for (int k = 0; k < noir.length; k++) {
                        if (noir[k][0] == blanc[indicePiece][0] + 1 && noir[k][1] == j)
                            possible = true;
                    }
                }
            }
            // Voir quand le pion est à l'origine et qu'il veut aller deux cases plus loin case[x+2][y]
            else if (blanc[indicePiece][0] + 2 == i) {
                for (int k = 0; k < noir.length; k++) {
                    if ((noir[k][0] == blanc[indicePiece][0] + 1 || noir[k][0] == blanc[indicePiece][0] + 2) && noir[k][1] == j)
                        possible = false;
                }
                possible = true;
            }
            possible = false;
        }
        else if(couleur==1){
            // Voir quand le pion essaie d'aller une ligne plus loin
            if (noir[indicePiece][0]-1==i) {
                // Voir s'il y a une pièce blanche sur la case[x-1][y]
                if (noir[indicePiece][1] == j) {
                    for (int k = 0; k < blanc.length; k++) {
                        if (blanc[k][0] == noir[indicePiece][0] - 1 && blanc[k][1] == j)
                            possible =  false;
                    }
                    possible = true;
                }
                // Voir s'il y a une pièce blanche sur la case[x-1][y-1]
                else if (j == noir[indicePiece][1] - 1) {
                    for (int k = 0; k < blanc.length; k++) {
                        if (blanc[k][0] == noir[indicePiece][0] - 1 && blanc[k][1] == j)
                            possible = true;
                    }
                }
                // Voir s'il y a une pièce blanche sur la case[x-1][y+1]
                else if (j == noir[indicePiece][1] + 1) {
                    for (int k = 0; k < blanc.length; k++) {
                        if (blanc[k][0] == noir[indicePiece][0] + 1 && blanc[k][1] == j)
                            possible = true;
                    }
                }
                possible = false;
            }
            // Voir quand le pion est à l'origine et qu'il veut aller deux cases plus loin
            else if (noir[indicePiece][0] - 2 == i) {
                for (int k = 0; k < blanc.length; k++) {
                    if ((blanc[k][0] == noir[indicePiece][0] -1 || blanc[k][0] == noir[indicePiece][0] - 2) && blanc[k][1] == j)
                        possible = false;
                }
                possible = true;
            }
        }
        return possible;

    }

    public static boolean deplacementTour(int tab[][], int indice, int i, int j){  //indice = position actuelle, i = futur x, j = futur y
        int x = tab[indice][0];
        int y = tab[indice][1];

        return ((i!=x && j== y) || (i==x && j!=y));    // (i!=x && j== y) --> deplacement horizontal    (i==x && j!=y) --> deplacement vertical
    }

    public static boolean deplacementFou(int tab[][], int indice, int i, int j){
        int x = tab[indice][0];
        int y = tab[indice][1];

        return ((Math.abs(x-i)) == (Math.abs(y-j)));  // le deplacement horizontal et vertical doivent avoir la meme valeur (diagonal).
        // afin de traiter tout les cas, valeur absolue evite d'avoir des valeur négatives i et j sup a x et y (deplacement vers le bas)é


    }

    public static boolean deplacementReine(int tab[][], int indice, int i, int j){ // combinaison d'un deplacement de tour ou de fou
        return (deplacementFou(tab, indice, i,j) || deplacementTour(tab, indice, i, j));
    }

    public static boolean deplacementCavalier(int tab[][], int indice, int i, int j){
        int x = tab[indice][0];
        int y = tab[indice][1];

        return((Math.abs(x- i)==1 && Math.abs(y-j)==2) || (Math.abs(x- i)==2 && Math.abs(y-j)==1));  // la valeur absolue permet de s'occuper de la symetrie, quelque soit la direction du cavalier
        //une fois éliminée la symetrie selon x et y, il y a deux possibilité de position
        // x+1 et y+2 ou x+2 et y+1;
    }

    public static boolean deplacementRoi(int tab[][], int indice, int i, int j){
        int x = tab[indice][0];
        int y = tab[indice][1];



        return ((Math.abs(y-j) == 1 && Math.abs(x-i) ==0) || (Math.abs(y-j) == 0 && Math.abs(x-i) ==1) || (Math.abs(y-j) == 1 && Math.abs(x-i) ==1));
    }


    public static boolean verificationDuChemin (int indicePiece, int i, int j, int[][] tabJoueur){
        boolean possible=false;
        if(indicePiece==1) possible = verificationDuCheminDame(indicePiece, i, j, tabJoueur);

        else if(indicePiece == 2 || indicePiece==3) possible = verificationDuCheminFou (indicePiece, i, j, tabJoueur);


        else if(indicePiece==6 || indicePiece==7) possible = verificationDuCheminTour (indicePiece, i, j, tabJoueur);

        else possible = true;


        return possible;
    }

    public static boolean verificationDuCheminTour(int indice, int i, int j,  int tabJoueur[][]){
        int x, y;
        x = tabJoueur[indice][0];
        y = tabJoueur[indice][1];


        if(x-i > 0){  // meme ligne, changement de colonne vers la gauche
            for(int k=1; k < x-i; k++){
                if(!caseDisponible(x-k, y, tabJoueur)){
                    return false;
                }

            }
        }

        if(x-i < 0){  // meme ligne changement de colonne vers la droite
            for(int k=1; k < x-i; k++){
                if(!caseDisponible(x+k, y,tabJoueur)){
                    return false;
                }

            }
        }

        if(y-j < 0){ // meme colonne changement de ligne vers le bas
            for(int k=1; k < x-i; k++){
                if(!caseDisponible(x, y+k, tabJoueur)){
                    return false;
                }

            }
        }

        if(y-j > 0){ // meme colonne changement de ligne vers le haut
            for(int k=1; k < x-i; k++){
                if(!caseDisponible(x, y-k, tabJoueur)){
                    return false;
                }

            }
        }
        return true;

    }

    public static boolean verificationDuCheminDame(int indice, int i, int j, int[][] tabJoueur){
        return (verificationDuCheminTour(indice, i, j, tabJoueur) || verificationDuCheminFou(indice, i, j, tabJoueur));
    }

    public static boolean verificationDuCheminFou (int indicePiece, int i, int j, int[][]tabJoueur){
        int x, y;

        x=tabJoueur[indicePiece][0];
        y=tabJoueur[indicePiece][1];


        //Partie en haut
        if (j > y){

            if (i > x) {//Partie en haut à droite
                for (int k = 0; x + k < i; k++)
                    if (!caseDisponible(x + k, y + k, tabJoueur))
                        return false;

            }else//Partie en haut à gauche
                for (int k = 0; x - k > i; k++)
                    if (!caseDisponible(x - k, y + k, tabJoueur))
                        return false;

        }else//Partie en bas

            if (i > x){//Partie en bas à droite
                for (int k = 0; x + k < i; k++) {
                    if (!caseDisponible(x + k, y - k, tabJoueur))
                        return false;
                }

            }else {//Partie en bas à gauche
                for (int k = 0; x - k > i; k++) {
                    if (!caseDisponible(x - k, y - k, tabJoueur))
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



    public static boolean echecRoi (int[][]tabEnnemi, int[][] tabJoueur, int couleur){
        int xRoi;
        int yRoi;

            xRoi = tabJoueur[0][0];
            yRoi = tabJoueur[0][1];
            for (int k=1; k<tabJoueur.length ; k++){
                if (tabJoueur[k][0] >= 0){
                    if (verificationDeplacementPossible(tabEnnemi, tabJoueur, k, xRoi, yRoi, couleur ) && verificationDuChemin (k, xRoi, yRoi, tabJoueur))
                        return true;
                }
            }
        return false;
    }




    public static void echecEtMat(){

    }


}
