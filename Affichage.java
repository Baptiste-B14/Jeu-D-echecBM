public class Affichage {

    public static void printBoard(int[][]tabBlanc, int[][] tabNoir){
        String [][] plateau = new String [8][8];
        String type;
        System.out.println();
        for(int i =0; i<tabBlanc.length; i++){

            if(tabBlanc[i][0] >=0 || tabBlanc[i][1] >=0){
                type = typePiece(i, 0);//stocke le type de la piece
                plateau[tabBlanc[i][0]][tabBlanc[i][1]] = type;// met dans la case correspondante aux positions de la pièce (non negative) le type de la pièce
            }

            if(tabNoir[i][0] >=0 || tabNoir[i][1] >=0){
                type = typePiece(i, 1);//stocke le type de la piece
                plateau[tabNoir[i][0]][tabNoir[i][1]] = type;// met dans la case correspondante aux positions de la pièce (non negative) le type de la pièce
            }
        }

        System.out.println("     1     2     3     4     5     6     7     8  ");

        char numLigne  = 65;

        for(int ligne = 0; ligne<plateau.length; ligne++){

            System.out.print(""+numLigne + " |");
            for(int colonne=0; colonne<plateau[ligne].length; colonne++){
                if(plateau[ligne][colonne] != null){
                    System.out.print( " _" + plateau[ligne][colonne] + "_ |");
                }else{
                    System.out.print(" _ _ |");
                }
            }
            numLigne++;
            System.out.println();

        }
        System.out.println();

    }


    public static String typePiece(int indice, int couleur){
        String chara;
        int code=0;

        if(couleur == 1) {
            if (indice == 0) code = 0x2654;//roi noir


            else if (indice == 1) code = 0x2655;//reine noir

            else if (indice == 2 || indice == 3) code = 0x2657;//fou noir

            else if (indice == 4 || indice == 5) code = 0x2658;//cavalier noir

            else if (indice == 6 || indice == 7) code = 0x2656;//tour noir

            else if (indice > 7) code = 0x2659;//pion noir

        }else{
            if (indice == 0) code = 0x265A;


            else if (indice == 1) code = 0x265B;

            else if (indice == 2 || indice == 3) code = 0x265D;

            else if (indice == 4 || indice == 5) code = 0x265E;

            else if (indice == 6 || indice == 7) code = 0x265C;

            else if (indice > 7) code = 0x265F;
        }

        chara = Character.toString((char)code);
        return chara;
    }
}