import java.util.Scanner;

/*
 * La classe fonction contient toute les fonctions essentielles au maintient des règles du jeu. Elle comprend donc des méthodes qui permettent la saisie des pièces, la saisie de leur futur déplacement
 * des méthodes qui vérifient que la position donnée est atteignable en respectant le mode de déplacement de la pièces, mais aussi que rien ne gêne son déplacement.
 * La classe contient les methodes qui constituent l'objectif des echecs, a savoir echec, et echec et mat.
 * Ces methodes seront appelées dans la classe Menu au menu opportun.
 */
public class Fonction {

    /**
     *La fonction demandeSaisie sert a demander au joueur de saisir les coordonées d'une case. Cette case doit contenir une pièce appartenant au joueur.
     * La saisie est forcée, l'utilisateur n'a d'autre choix que de saisir une case correcte, et contenant l'une de ses pièces.
     * @param tabJoueur le tableau 2D de int contenant les positions de toutes les pièces du joueur en cours.
     * @return un int correspondant au type de la pièces.
     *
     */

    public static int demandeSaisie( int[][] tabJoueur){//demande au joueur la case qui contient la pièce à saisir. 
        Scanner sc = new Scanner(System.in).useDelimiter("\n");//la saisie ne se fait pas en demandant le type de la pièce, mais par la case conteant la pièce.
        int indicePiece = -1;
        boolean caseNonVide = false;

        do {
            String CaseEchiquier = "";
            do {//saisie forcée d'une case existante, respectant le format LettreChiffre
                System.out.println("Saisir la position de la pièce à déplacer: (format Lettre Chiffre: B5)");
                CaseEchiquier = sc.nextLine().toUpperCase();
            } while (CaseEchiquier.length() != 2 || (CaseEchiquier.charAt(0) < 65 || CaseEchiquier.charAt(0) > 72) || (CaseEchiquier.charAt(1) < 49 || CaseEchiquier.charAt(1) > 56));
            //Si la lettre n'est pas comprise entre A et H et le chiffre entre 1 et 8 (comparaison avec le code ASCII), la saisie est recommencée.
            for (int i = 0; i < tabJoueur.length; i++) {//La boucle for permet de vérifier que la case demandé contient une pièce du joueur
                if ((tabJoueur[i][0] == CaseEchiquier.charAt(0) - 65) && tabJoueur[i][1] == CaseEchiquier.charAt(1) - 49) {
                    indicePiece = i;
                    caseNonVide = true;
                }
            }
            if(!caseNonVide) System.out.println("La case est vide ou contient une pièce qui n'est pas a vous");
        }while(!caseNonVide);//force la saisie d'une case non vide 

        System.out.println("Pièce selectionnée.");
        return indicePiece;
    }


    /*
     * La fonction demandePosition fonctionne de façon similaire à demandeSaisie. Son but est de forcer l'utilisateur a donner une position correcte.
     * @return un tableau de int contenant les positions x et y de la case visée.
     */
    public static int[] demandePosition(){
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        int[] position = new int[2];
        String CaseEchiquier;

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
        // On vérifie si la case sélectionnée n'est pas déjà occupée par un pion de la même couleur
        for (int k = 0; k < tabJoueur.length; k++) {
            if (tabJoueur[k][0] == i && tabJoueur[k][1] == j) {
                System.out.println("La case est déjà occupée par une de vos pièces");
                return false;
            }
        }
        return true;

    }

    public static boolean verificationDeplacementPossible( int indicePiece, int i, int j, int couleur, int tabJoueur[][],int tabEnnemi[][]){
        boolean possible = false;

        if(indicePiece == 0) possible = deplacementRoi(indicePiece, i, j, tabJoueur);
        else if(indicePiece == 1) possible = deplacementReine(indicePiece, i, j, tabJoueur);
        else if(indicePiece == 2 || indicePiece == 3) possible = deplacementFou(indicePiece, i, j, tabJoueur);
        else if(indicePiece == 4 || indicePiece == 5) possible = deplacementCavalier(indicePiece, i, j, tabJoueur);
        else if(indicePiece == 6 || indicePiece == 7) possible = deplacementTour(indicePiece, i, j, tabJoueur);
        else if(indicePiece >7 ) possible = deplacementPion(indicePiece, i, j, couleur, tabJoueur, tabEnnemi);

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
                    if (!caseDisponible(i, j, tabEnnemi))
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
                    if (!caseDisponible(i, j, tabEnnemi))
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

    public static void actualisationEchiquier( int indicePiece, int i, int j, int[][] tabJoueur, int[][]tabEnnemi){

        for(int parcours = 0; parcours<tabEnnemi.length; parcours++){
            if((tabEnnemi[parcours][0] == i) && (tabEnnemi[parcours][1] == j)){
                tabEnnemi[parcours][0] = -1;
                tabEnnemi[parcours][1] = -1;
            }
        }
        tabJoueur[indicePiece][0] = i;
        tabJoueur[indicePiece][1] = j;
    }

    public static boolean echecRoi (int couleur, int[][] tabJoueur, int[][]tabEnnemi){
        int xRoi;
        int yRoi;
        // On vérifie si le Roi du joueur est mis en échec.
        xRoi = tabJoueur[0][0];
        yRoi = tabJoueur[0][1];
        // On parcourt toutes les pièces ennemies pour voir si au moins une met en échec le roi du joueur.
        for (int k=1; k<tabEnnemi.length ; k++){//k commence a 1 car on ne peut pas mettre en echec avec un roi
            //On vérifie qu'on travaille avec une pièce 
            if (tabEnnemi[k][0] >= 0){
                if (verificationDeplacementPossible(k, xRoi, yRoi, ((couleur+1)%2),tabEnnemi, tabJoueur ) && verificationDuChemin (k, xRoi, yRoi, tabEnnemi, tabJoueur)) return true;
            }
        }

        return false;
    }
    // Voir quand le pion essaie d'aller une ligne plus loin
    public static boolean echecEtMat(int couleur, int[][] tblanc, int [][] tnoir, boolean aide, int[][] tabSolutions){
        // On parcourt toutes les pièces du joueur ennemi pour voir si chacun de leur déplacement
        // ne permettrait pas d'éviter l'échec du roi
        int x, y;
        // On déclare les tableaux fictifs
        int[][] tabJoueur = new int[16][2];
        int[][] tabEnnemi = new int[16][2];
        //
        //
        // On met les tableaux fictifs à la valeur des tableaux permanents

        //Joueur en cours.
        if (couleur == 0) {//copie par for
            for(int i = 0; i<tblanc.length; i++){
                for(int j =0; j<tblanc[i].length; j++) {
                    tabJoueur[i][j] = tblanc[i][j];
                    tabEnnemi[i][j] = tnoir[i][j];
                }
            }
        }else{
            for(int i = 0; i<tnoir.length; i++){
                for(int j =0; j<tnoir[i].length; j++) {
                    tabJoueur[i][j] = tnoir[i][j];
                    tabEnnemi[i][j] = tblanc[i][j];
                }
            }
        }

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
                    if (caseDisponible(x, y, tabEnnemi) && verificationDeplacementPossible(indicePiece, x, y, ((couleur+1)%2), tabEnnemi, tabJoueur) && verificationDuChemin(indicePiece, x, y, tabEnnemi, tabJoueur)) {
                        actualisationEchiquier(indicePiece, x, y, tabEnnemi, tabJoueur);
                        if (!echecRoi(((couleur + 1) % 2), tabEnnemi, tabJoueur)){//Ne pas modifier l'ordre des tab param. On cherche le roi ennemi (fin du tour)
                            if (!solutions(aide, x, y, tabSolutions))
                                return false;
                        }


                        // On ré-actualise les tableaux d'origine
                        // Pour ne pas enregistrer le déplacement fictif de la pièce

                        if (couleur == 0) {//copie par for
                            for(int i = 0; i<tblanc.length; i++){
                                for(int j =0; j<tblanc[i].length; j++) {
                                    tabJoueur[i][j] = tblanc[i][j];
                                    tabEnnemi[i][j] = tnoir[i][j];
                                }
                            }
                        }else{
                            for(int i = 0; i<tnoir.length; i++){
                                for(int j =0; j<tnoir[i].length; j++) {
                                    tabJoueur[i][j] = tnoir[i][j];
                                    tabEnnemi[i][j] = tblanc[i][j];
                                }
                            }
                        }
                    }
        }

        System.out.println("Le joueur adverse est en échec, vous avez gagné !");
        return true;
    }
    public static boolean solutions (boolean aide, int x, int y, int[][] tabSolutions){
        int i=0;
        if (aide){
            while(i<5 && tabSolutions[i][0]>=0) i++;
            if (i!=5) {tabSolutions[i][0]= x; tabSolutions[i][1]= y;}
            else {return false;}
        }
        else{
            return false;
        }
        return true;
    }
    public static void affichageSolutions (boolean aide, int[][] tabSolutions){
        char coordonnees;
        System.out.println("Vérification affichageSolutions");
        if(aide && tabSolutions[0][0]>=0){
            System.out.print("Pour éviter l'échec, vous pouvez déplacer une de vos pièces sur la/les case(s) : ");
            for (int i=0; i<tabSolutions.length || tabSolutions[i+1][0]>=0; i++){
                coordonnees = (char)(tabSolutions[i][0]+17);
                System.out.print(coordonnees);
                coordonnees = (char)(tabSolutions[i][1]);
                System.out.print(coordonnees +" ; ");
            }
        }

    }
}