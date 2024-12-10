import settings.Data;

import java.util.concurrent.*;

public class Simulation {
    private static final long DAY_DURATION = 1500;

    public static void go(int days) {

        ExecutorService animalLiveService = Executors.newFixedThreadPool(Data.CORES);
        ScheduledExecutorService newDayService = Executors.newScheduledThreadPool(1);

        newDayService.scheduleAtFixedRate(new Sunrise(animalLiveService), 0, DAY_DURATION, TimeUnit.MILLISECONDS);

        try {
            Thread.sleep(days * DAY_DURATION);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        newDayService.shutdown();
        animalLiveService.shutdown();

    }
}
