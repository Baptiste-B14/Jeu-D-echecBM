
public class Affichage {

    /**
     * La méthode printBoard s'occupe d'afficher l'échiquier et les pièces qui sont dessus.
     * Pour cela, un nouveau tableau 2D de 8x8 est créé et contiendra un chaine de caractère dans chaque case, qui correspond à "l'état" de la case.
     * Ensuite, une fois chaque case remplie, un parcours régulier du tableau affichera une chaine de caractère en fonction du contenu du tableau.
     * @param tabBlanc le tableau contenant les pièces blanches
     * @param tabNoir le tableau contenant les pièces noires
     * @param pBlanc le tableau contenant les promotions de chaque pions blancs
     * @param pNoir le tableau contenant les promotions de chaque pions noirs
     */
    public static void printBoard(int[][]tabBlanc, int[][] tabNoir, char[] pBlanc, char[] pNoir){
        String [][] plateau = new String [8][8];
        String type;
        System.out.println();
        for(int i =0; i<tabBlanc.length; i++){

            if(tabBlanc[i][0] >=0 || tabBlanc[i][1] >=0){
                type = typePiece(i, 0, pBlanc);//stocke le type de la piece
                plateau[tabBlanc[i][0]][tabBlanc[i][1]] = type;// met dans la case correspondante aux positions de la pièce (non negative) le type de la pièce
            }

            if(tabNoir[i][0] >=0 || tabNoir[i][1] >=0){
                type = typePiece(i, 1, pNoir);//stocke le type de la piece
                plateau[tabNoir[i][0]][tabNoir[i][1]] = type;// met dans la case correspondante aux positions de la pièce (non negative) le type de la pièce
            }
        }

        System.out.println("     1     2     3     4     5     6     7     8  ");//ligne "0", affiche les numeros des colonnes

        char numLigne  = 65;//sera incrémenté toute les lignes. Affiche la lettre de la ligne

        for(int ligne = 0; ligne<plateau.length; ligne++){//parcours régulier du tableau contenant les cases.

            System.out.print(""+numLigne + " |");//colonne "0"
            for(int colonne=0; colonne<plateau[ligne].length; colonne++){
                if(plateau[ligne][colonne] != null){//regarde si la case est vide ou contient une pièce
                    System.out.print( " _" + plateau[ligne][colonne] + "_ |");//affiche la pièce (ici un code unicode afin d'avoir le symbole de chaque pièce, plutot qu'une chaine de char (ex BC pour cavalier blanc)
                }else{
                    System.out.print(" _ _ |");
                }
            }
            numLigne++;
            System.out.println();

        }
        System.out.println();

    }

    /**
     * Determine le codage unicode de chaque pièce en fonction de son type.
     * @param indicePiece l'indice de la pièce dans le tableau des positions. Il correspond ainsi à son type de façon induite (norme de construction du programme)
     * @param couleur la couleur de la pièce, les codes unicode ne sont pas les mêmes selon la couleur
     * @param promotion le tableau de promotion des pions de correspondant à la couleur
     * @return une chaine de caractères correspondant au code unicode de la pièce
     */
    public static String typePiece(int indicePiece, int couleur, char []promotion){
        String chara;
        int code=0;

        if(couleur == 1) {//determine quelle est la couleur de la pièce
            if (indicePiece == 0) code = 0x2654;//roi noir


            else if (indicePiece == 1 || (indicePiece>7 && promotion[indicePiece-8] == 68)) code = 0x2655;//reine noir

            else if (indicePiece == 2 || indicePiece == 3 || (indicePiece>7 && promotion[indicePiece-8] == 70)) code = 0x2657;//fou noir

            else if (indicePiece == 4 || indicePiece == 5 || (indicePiece>7 && promotion[indicePiece-8] == 67)) code = 0x2658;//cavalier noir

            else if (indicePiece == 6 || indicePiece == 7 || (indicePiece>7 && promotion[indicePiece-8] == 84)) code = 0x2656;//tour noir

            else if (indicePiece > 7 ) code = 0x2659;//pion noir

        }else{
            if (indicePiece == 0) code = 0x265A;


            else if (indicePiece == 1 || (indicePiece>7 && promotion[indicePiece-8] == 68)) code = 0x265B;

            else if (indicePiece == 2 || indicePiece == 3 || (indicePiece>7 && promotion[indicePiece-8] == 70)) code = 0x265D;

            else if (indicePiece == 4 || indicePiece == 5 || (indicePiece>7 && promotion[indicePiece-8] == 67)) code = 0x265E;

            else if (indicePiece == 6 || indicePiece == 7 || (indicePiece>7 && promotion[indicePiece-8] == 84)) code = 0x265C;

            else if (indicePiece > 7 ) code = 0x265F;
        }

        chara = Character.toString((char)code);//convertit le code unicode en une string compréhensible du programme
        return chara;
    }


    /**
     * Cette méthode est uniquement utile à l'affichage. En effet les solutions sont systématiquement déterminées, mais pas forcément affichées.
     * @param aide boolean indiquant si l'aide est active
     * @param tabSolutions tableau 2D contenant les solutions possibles pour sortir de l'echec.
     */
    public static void affichageSolutions (boolean aide, int[][] tabSolutions){
        char coordonnees;
        System.out.println();
        // On vérifie que le tableau de solutions n'est pas vide
        if(aide && tabSolutions[0][0]>=0){//parcours du tableau des solutions
            System.out.print("Pour éviter l'échec, vous pouvez déplacer une de vos pièces sur la/les case(s) : ");
            // On affiche toutes les solutions
            for (int i=0; i<tabSolutions.length && tabSolutions[i][0]>=0; i++){
                // On affiche le caractère
                coordonnees = (char)(tabSolutions[i][0]+65);
                System.out.print(coordonnees);
                // On affiche le chiffre
                coordonnees = (char)(tabSolutions[i][1]+49);
                System.out.print(coordonnees +" ; ");
            }
            System.out.println();
        }

    }


}
