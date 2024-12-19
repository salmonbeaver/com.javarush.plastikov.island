import entity.Island;
import settings.Data;

public class App {


    public static final int OBSERVE_DAYS = 10;

    public static void main(String[] args) {
        Data.init();
        Island.init();

        Simulation.go();
    }
}
