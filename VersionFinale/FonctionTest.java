import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FonctionTest {
    @Test
    void caseDisponible(){


        int[][] tblanc = {{1,7},{-6,-3},{-2,-5},{-1,-5},{-7,-4},{-1,-6},{-1,-1},{3,1},{-3,-3},{-1,-1},{-1,-2},{6,3},{-1,-4},{-1,-5},{-1,-6},{-1,-7}};
        int[][] tnoir = {{5,1},{-7,-3},{-7,-2},{-7,-5},{-7,-1},{-7,-6},{-7,-1},{-7,-7},{-6, -1},{-6,-1},{-6,-2},{2,4},{-6,-4},{-6,-5},{-6,-6},{-6,-7}};

        assertTrue(Fonction.caseDisponible(1, 6, tblanc), "Pas de pièce sur la case");
        assertFalse(Fonction.caseDisponible(1, 7 , tblanc), "Pièce sur la case indiquée de la couleur du joueur");
        assertTrue(Fonction.caseDisponible(5 , 1, tblanc), "Case occupée par une pièce noir, mais donc disponible pour les blancs");
        assertTrue(Fonction.caseDisponible(5,5 ,tnoir), "Milieu de l'échiquier");
        assertFalse(Fonction.caseDisponible(5, 1, tnoir), "Case occupée par le roi noir");
    }


    @Test
    void deplacementPion(){

        int[][] tblanc = {{5,2},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{1,5},{2, 6},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};
        int[][] tnoir = {{7,0},{0,1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{4,3},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};
        int[][] tnoir2 = {{7,0},{0,1},{-1,-1},{-1,-1},{-1,-1},{2,4},{-1,-1},{-1,-1},{4,3},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};

        assertTrue(Fonction.deplacementPion(9, 2, 5, 0, tblanc, tnoir), "Déplacement en avant d'une case");
        assertTrue(Fonction.deplacementPion(9, 3, 5, 0, tblanc, tnoir ), "Déplacement de deux cases en avant avec départ sur la première ligne");
        assertFalse(Fonction.deplacementPion(10, 4, 6, 0, tblanc, tnoir), "Deplacement de deux cases en avant avec départ sur la deuxième ligne");
        assertFalse(Fonction.deplacementPion(9, 2, 4, 0, tblanc, tnoir ), "Déplacement vers la gauche, sans pièce à prendre");
        assertTrue(Fonction.deplacementPion(9, 2, 4, 0, tblanc, tnoir2), "Déplacement vers la gauche, pièce ennemi à prendre");
        assertFalse(Fonction.deplacementPion(9, 1, 5, 0, tblanc, tnoir ), "Déplacement arrière");
    }



    @Test
    void deplacementTour(){
        int[][] tblanc = {{5,2},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{0,1},{-1,-1},{-1,-1},{-1,-5},{-2,-6},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};

        assertTrue(Fonction.deplacementTour(6, 7, 1, tblanc), "Déplacement vertical");
        assertTrue(Fonction.deplacementTour(6, 0,4, tblanc), "Déplacement horizontal");
        assertFalse(Fonction.deplacementTour(6, 4, 5, tblanc), "Déplacement non conforme");
    }


    @Test
    void deplacementCavalier(){
        int[][] tblanc = {{5,2},{-1,-1},{-1,-1},{-1,-1},{0,6},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-5},{-2,-6},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};

        assertTrue(Fonction.deplacementCavalier(4, 2,5, tblanc), "Déplacement correct deux en bas, un a gauche");
        assertTrue(Fonction.deplacementCavalier(4, 1,4, tblanc), "Déplacement correct un en bas, deux a gauche");
        assertFalse(Fonction.deplacementCavalier(4, 2,3, tblanc), "Déplacement en diagonale");

    }


    @Test
    void deplacementFou(){
        int[][] tblanc = {{5,2},{-1,-1},{-1,-1},{0,2},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-5},{-2,-6},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};

        assertTrue(Fonction.deplacementFou(3, 2,4, tblanc), "Déplacement correct diagonale vers la droite");
        assertTrue(Fonction.deplacementFou(3, 1,1, tblanc), "Déplacement correct diagonale vers la gauche");
        assertFalse(Fonction.deplacementFou(3, 2,2, tblanc), "Déplacement en diagonale");
    }
}
