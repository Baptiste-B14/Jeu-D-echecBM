 public class Chrono {


     /**
      * Récupère la valeur en millisecondes du temps actuel.
      * @return un long correspondant au temps du système
      */
    public static long start()
    {
        long tempsDepart=System.currentTimeMillis();
        return tempsDepart;
    }

     /**
      * La méthode pause() calcule le temps entre le départ et l'arret du chrono
      * @param tempsDepart la valeur correspondant au temps du système au moment du lancement du chronomètre
      * @return la différence entre le l'heure de fin du chronomètre et l'heure de départ. Elle correspond au temps écoulé depuis le lancement du chronomètre
      */
     public static long pause(long tempsDepart)
    {
        long pauseDepart =System.currentTimeMillis();
        return ( pauseDepart - tempsDepart);
    }

     /**
      * La méthode convert() sert à convertir le temps obtenu en millisecondes en temps expirmé en minutes-secondes.
      * Le résultat sera affiché afin d'avertir le joueur du temps total qu'il a passé.
      * @param temps
      */
     public static void convert(long temps){
        int minute  = (int)(temps *0.001 * 1/60);
        int seconde  = (int) (temps - (minute*60*1000)) /1000;

        System.out.println("Minutes : " + minute + " | Secondes : " + seconde);

    }


} // class Chrono

