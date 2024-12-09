import entity.Island;
import settings.Data;

import java.util.concurrent.*;

public class Simulation {

    public static final ExecutorService animalLiveService = Executors.newFixedThreadPool(Data.CORES);

    public static void go(int days) {

        CountDownLatch latch = new CountDownLatch(1);

        try (ScheduledExecutorService newDayService = Executors.newScheduledThreadPool(Data.CORES)) {

            for (int i = 0; i < days; i++) {

                newDayService.scheduleAtFixedRate(new Sunrise(latch), 0, 2, TimeUnit.SECONDS);

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("DAY #" + (i + 1));
                System.out.println(Island.getStatus());
                System.out.println("-".repeat(210));
            }
        }

        animalLiveService.shutdown();
    }
}
