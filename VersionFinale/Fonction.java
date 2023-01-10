import java.util.Scanner;

public class Fonction {

    /**
     * Méthode de saisie de la pièceà déplacer. La saisie est forcée afin d'avoir une position correcte, si erreur avant.
     * @param tabJoueur les tableau des positions des pièces du joueur
     * @return entier, l'indice de pièce saisie
     */
    public static int demandeSaisie( int[][] tabJoueur){
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        int indicePiece = -1;
        boolean caseNonVide = false;

        do {
            String CaseEchiquier = "";
            do {
                System.out.print("Saisir la position de la pièce à déplacer : ");
                CaseEchiquier = sc.nextLine().toUpperCase();//ignore la casse
            } while (CaseEchiquier.length() != 2 || (CaseEchiquier.charAt(0) < 65 || CaseEchiquier.charAt(0) > 72) || (CaseEchiquier.charAt(1) < 49 || CaseEchiquier.charAt(1) > 56));//force la saisie selon le format LettreChiffre

            for (int i = 0; i < tabJoueur.length; i++) {//cherche si une pièce dans le tableau possède la position fournie. Si non, alors case vide.
                if ((tabJoueur[i][0] == CaseEchiquier.charAt(0) - 65) && tabJoueur[i][1] == CaseEchiquier.charAt(1) - 49) {
                    indicePiece = i;
                    caseNonVide = true;
                }
            }
            if(!caseNonVide) System.out.println("La case est vide ou contient une pièce qui n'est pas a vous ");
        }while(!caseNonVide);

        System.out.print("Pièce selectionnée. ");
        return indicePiece;
    }

    /**
     * Meme principe que la demande de saisie. La saisie est forcée afin d'avoir une case valide
     * @return un tableau 1D contenant le x et le y de la position à atteindre. Ces coordonées seront utiles pour vérifier plusieurs propritées de la pièces
     */
    public static int[] demandePosition(){
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        int[] position = new int[2];
        String CaseEchiquier;

        do {
            System.out.print("Saisir une position : ");
            CaseEchiquier = sc.nextLine().toUpperCase();
        }while(CaseEchiquier.length() != 2 || (CaseEchiquier.charAt(0) < 65 || CaseEchiquier.charAt(0) > 72) || (CaseEchiquier.charAt(1) < 49 || CaseEchiquier.charAt(1) > 56));

        position[0] = CaseEchiquier.charAt(0) - 65;
        position[1] = CaseEchiquier.charAt(1) - 49;

        return position;
    }

    /**
     * Vérifie si la case correspondant aux positions données est vide de pièces du joueur (pas du joueur ennemi, car on peut prendre sa place)
     * @param i indice de la ligne où va être posée la pièce
     * @param j indice de la colonne où va être posée la pièce
     * @param tabJoueur le tableau des positions des pièce  du joueur
     * @return
     */
    public static boolean caseDisponible (int i, int j, int[][] tabJoueur){
        // On vérifie si la case sélectionnée n'est pas déjà occupée par un pion de la même couleur
        for (int k = 0; k < tabJoueur.length; k++) {
            if (tabJoueur[k][0] == i && tabJoueur[k][1] == j) {

                return false;
            }
        }
        return true;

    }

    /**
     * En fonction de l'indice fourni (et de la promotion si pion), la méthode appelera la bonne fonction de vérification correspondant à la pièce
     * @param indicePiece entier correspondant à l'indice de la pièce dans le tableau de position, c'est aussi son type
     * @param i position x
     * @param j position y
     * @param couleur couleur du joueur (pour les pions)
     * @param tabJoueur le tableau de position des pièces du joueur
     * @param tabEnnemi le tableau de position des pièces du joueur ennemi
     * @param promoJoueur le tableau de promotion du joueur
     * @return le boolean validant le schema de déplacement
     */
    public static boolean verificationDeplacementPossible( int indicePiece, int i, int j, int couleur, int tabJoueur[][],int tabEnnemi[][], char[] promoJoueur){
        boolean possible = false;

        if(indicePiece == 0) possible = deplacementRoi(indicePiece, i, j, tabJoueur);
        else if(indicePiece == 1 || (indicePiece>7 && promoJoueur[indicePiece-8] == 68)) possible = deplacementReine(indicePiece, i, j, tabJoueur);
        else if(indicePiece == 2 || indicePiece == 3 || (indicePiece>7 && promoJoueur[indicePiece-8] == 70)) possible = deplacementFou(indicePiece, i, j, tabJoueur);
        else if(indicePiece == 4 || indicePiece == 5 || (indicePiece>7 && promoJoueur[indicePiece-8] == 67)) possible = deplacementCavalier(indicePiece, i, j, tabJoueur);
        else if(indicePiece == 6 || indicePiece == 7 || (indicePiece>7 && promoJoueur[indicePiece-8] == 84)) possible = deplacementTour(indicePiece, i, j, tabJoueur);
        else if(indicePiece >7 ) possible = deplacementPion(indicePiece, i, j, couleur, tabJoueur, tabEnnemi);
//67 = c, 68 = d, 70 = f
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
            else if (tabJoueur[indicePiece][0] + 2 == i && tabJoueur[indicePiece][0]==1) {
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
                    if (!caseDisponible(i, j, tabJoueur))
                        possible= true;
                }
            }
            // Voir quand le pion est à l'origine et qu'il veut aller deux cases plus loin case[x-2][y]
            else if (tabJoueur[indicePiece][0] - 2 == i && tabJoueur[indicePiece][0]==6) {
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
        // afin de traiter tout les cas, valeur absolue evite d'avoir des valeurs négatives i et j supérieur à x et y (deplacement vers le bas)


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


    /**
     * Fonctionnement similaire à verificationDéplacement()
     *
     */
    public static boolean verificationDuChemin (int indicePiece, int i, int j, int[][] tabJoueur, int[][]tabEnnemi, char[]promoJoueur){
        boolean possible=false;
        if(indicePiece==1 ||  (indicePiece>7 && promoJoueur[indicePiece-8] == 68)) possible = (verificationDuCheminDame(indicePiece, i, j, tabJoueur, tabEnnemi));
        else if(indicePiece == 2 || indicePiece==3 || (indicePiece>7 && promoJoueur[indicePiece-8] == 70)) possible = verificationDuCheminFou (indicePiece, i, j, tabJoueur, tabEnnemi);
        else if(indicePiece==6 || indicePiece==7 || (indicePiece>7 && promoJoueur[indicePiece-8] == 84)) possible = verificationDuCheminTour (indicePiece, i, j, tabJoueur, tabEnnemi);
        else possible = true;

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

    public static boolean echecRoi (int couleur, int[][] tabJoueur, int[][]tabEnnemi, char[] promoJoueur){
        int xRoi;
        int yRoi;
        // On vérifie si le Roi du joueur est mis en échec.
        xRoi = tabJoueur[0][0];
        yRoi = tabJoueur[0][1];
        // On parcourt toutes les pièces ennemies pour voir si au moins une met en échec le roi du joueur.
        for (int k=1; k<tabEnnemi.length ; k++){//k commence a 1 car on ne peut pas mettre en echec avec un roi
            //On vérifie qu'on travaille avec une pièce
            if (tabEnnemi[k][0] >= 0){
                if (verificationDeplacementPossible(k, xRoi, yRoi, ((couleur+1)%2),tabEnnemi, tabJoueur, promoJoueur ) && verificationDuChemin (k, xRoi, yRoi, tabEnnemi, tabJoueur, promoJoueur)) return true;
            }
        }

        return false;
    }
    // Voir quand le pion essaie d'aller une ligne plus loin
    public static boolean echecEtMat(int couleur, int[][] tblanc, int [][] tnoir, char[] pBlanc, char[] pNoir, char[] promoJoueur, boolean aide, int[][] tabSolutions){
        // On parcourt toutes les pièces du joueur ennemi pour voir si chacun de leur déplacement
        // ne permettrait pas d'éviter l'échec du roi
        int x, y;
        int nbSolutions=0;
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
            for(int i = 0; i<pBlanc.length; i++){
                promoJoueur[i] = pBlanc[i];
            }
        }else{
            for(int i = 0; i<tnoir.length; i++){
                for(int j =0; j<tnoir[i].length; j++) {
                    tabJoueur[i][j] = tnoir[i][j];
                    tabEnnemi[i][j] = tblanc[i][j];
                }
            }
            for(int i = 0; i<pNoir.length; i++){
                promoJoueur[i] = pNoir[i];
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
                    if (caseDisponible(x, y, tabEnnemi) && verificationDeplacementPossible(indicePiece, x, y, ((couleur+1)%2), tabEnnemi, tabJoueur, promoJoueur) && verificationDuChemin(indicePiece, x, y, tabEnnemi, tabJoueur, promoJoueur)) {
                        actualisationEchiquier(indicePiece, x, y, tabEnnemi, tabJoueur);
                        if (!echecRoi(((couleur+1)%2), tabEnnemi,tabJoueur, promoJoueur)) {//Ne pas modifier l'ordre des tab param. On cherche le roi ennemi (fin du tour)
                            nbSolutions++;
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

        if (nbSolutions<1) System.out.println("Le joueur adverse est en échec, vous avez gagné !");
        return (nbSolutions<1);
    }


    /**
     * Vérifie que si le pion peut accéder à une promotion
     * @param indicePiece
     * @param couleur
     * @param tabJoueur
     * @param promotion
     * @return le boolean validant la promotion
     */
    public static boolean promotionPossible(int indicePiece, int couleur, int[][] tabJoueur, char [] promotion){
        if(indicePiece<7) return false;//la pièce doit être un pion
        if(promotion[indicePiece-7] != 'p') return false;//le pion ne doit pas avoir de promotion 'p' est la valeur pas défaut
        int derniereLigne;
        if(couleur == 0) derniereLigne = 7;//determine la dernière ligne en fonction de la couleur
        else derniereLigne = 0;

        return tabJoueur[indicePiece][0]== derniereLigne;//vérifie que le ppion est bein sur sa dernière ligne, sinon il ne peut pas accéder à une promotion
    }


    public static void promotion(int indicePiece, char[] promoJoueur){
        Scanner sc = new Scanner(System.in).useDelimiter("\n");

        String promotion;

        do {//saisie forcée d'un type de pièce
            System.out.println("Saisir un type de pièce : Dame | Fou | Cavalier | Tour (D, F, C, T)");
            promotion = sc.nextLine().toUpperCase();
        }while(promotion.length() != 1 || ((promotion.charAt(0) != 67 && promotion.charAt(0) != 68) && (promotion.charAt(0) != 70 && promotion.charAt(0) != 84)));  //67 = c, 68 = d, 70 = f
        System.out.println("Promotion effectuée");
        promoJoueur[indicePiece-7] = promotion.charAt(0);//change la valeur de la promotion dans le tableau


    }

    public static boolean solutions (boolean aide, int x, int y, int[][] tabSolutions){
        int i=0;
        if (aide){
            while(i<5 && tabSolutions[i][0]>=0) i++;
            if (i<5) {
                int j=0;
                while(j<i && (tabSolutions[j][0]!=x || tabSolutions[j][1]!=y)) j++;
                if (j==i) {
                    tabSolutions[i][0] = x;
                    tabSolutions[i][1] = y;
                }
            }
            else {return false;}
        }
        else{
            return false;
        }
        return true;
    }

}
