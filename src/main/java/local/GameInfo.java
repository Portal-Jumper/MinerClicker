package local;

public class GameInfo {

    public static int unExperienced = 0;
    public static int miner = 0;
    public static int engineer = 0;
    public static int analyst = 0;
    public static int robot = 0;
    public static int unExperiencedPrice = 200;
    public static int minerPrice = 1000;
    public static int engineerPrice = 5000;
    public static int analystPrice = 20000;
    public static int robotPrice = 50000;
    public static int restarts = 0;
    public static int health = 20;
    public static int gemsPerClick = 1;
    public static double gems = 0;
    public static double gemsPerSecond = (1 + (unExperienced * 1.2) + (miner * 3.8) + (engineer * 8.6) +
            (analyst * 25.2) + (robot * 63.2)) * (1 + (restarts * 0.25));

    public static void recalculate() {
        gemsPerSecond = (5.2 + (unExperienced * 1.2) + (miner * 3.8) + (engineer * 8.6) +
                (analyst * 25.2) + (robot * 63.2)) * (1 + (restarts * 0.25));
    }

}
