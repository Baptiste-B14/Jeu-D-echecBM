 public class Chrono {

    private static long tempsDepart=0;
    private static long tempsFin=0;
    private static long duree = 0;
    private static long pauseFin=0;


    public static long start()
    {
        tempsDepart=System.currentTimeMillis();
        tempsFin=0;

        pauseFin=0;

        return tempsDepart;
    }

    public static long pause(long tempsActuel)
    {
        long pauseDepart =System.currentTimeMillis();

        return (tempsActuel+ pauseDepart);
    }

    public static long resume(long pauseDepart)
    {
        pauseFin=System.currentTimeMillis();
        tempsDepart=tempsDepart+pauseFin-pauseDepart;
        tempsFin=0;

        pauseFin=0;
        duree=0;
        return tempsDepart;
    }

    public static void stop()
    {
        if(tempsDepart==0) {return;}
        tempsFin=System.currentTimeMillis();
        //duree=(tempsFin-tempsDepart) - (pauseFin-pauseDepart);
        tempsDepart=0;
        tempsFin=0;

        pauseFin=0;
    }

} // class Chrono

