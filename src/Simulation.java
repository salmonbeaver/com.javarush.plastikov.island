import lombok.Getter;
import settings.Data;

import java.util.concurrent.*;

public class Simulation {
    @Getter
    private static final int DAYS = App.OBSERVE_DAYS;
    private static final int DAY_DURATION = 1500;
    @Getter
    private static final ScheduledExecutorService newDayService = Executors.newScheduledThreadPool(Data.CORES);
    private static final ExecutorService animalLiveService = Executors.newFixedThreadPool(Data.CORES);

    public static void go() {
        CountDownLatch generalLatch = new CountDownLatch(DAYS);
        newDayService.scheduleAtFixedRate(new Sunrise(animalLiveService, newDayService, generalLatch), 0, DAY_DURATION, TimeUnit.MILLISECONDS);

        try {
            generalLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (!newDayService.isShutdown()) {
            try {
                Thread.sleep(1000);
                animalLiveService.shutdownNow();
                newDayService.shutdownNow();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Потоки закончили работу");
    }
}
