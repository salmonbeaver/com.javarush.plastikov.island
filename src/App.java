import settings.Data;
import entity.*;

import java.util.concurrent.*;

public class App {

    public static void main(String[] args) {
        Data.init();
        Island.init();

        System.out.println("СТАРТОВОЕ СОСТОЯНИЕ ОСТРОВА: ");
        System.out.println(Island.getStatus());
        System.out.println("-".repeat(210));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int dayCount = 0;

        ScheduledExecutorService scheduledPlantService = Executors.newScheduledThreadPool(2);
        try (ExecutorService animalLiveService = Executors.newFixedThreadPool(Data.CORES)) {

            while (Data.OBSERVE_DAYS > 0) {
                dayCount++;

                for (int i = 0; i < Island.SIZE; i++) {
                    animalLiveService.execute(Island.getCell(i));
                }

                for (int i = 0; i < Island.SIZE; i++) {

                    try {
                        Island.getCell(i).LATCH.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println(Island.getStatus());
            }
        }
    }

}
