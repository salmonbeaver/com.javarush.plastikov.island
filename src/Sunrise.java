import entity.Animal;
import entity.Cell;
import entity.Island;
import entity.Plant;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

public class Sunrise implements Runnable {
    private final ExecutorService animalLiveService;
    private final ExecutorService newDayService;
    private static int dayCount = 0;
    private CountDownLatch generalLatch;

    public Sunrise(ExecutorService animalLiveService, ScheduledExecutorService newDayService, CountDownLatch genLatch) {
        this.animalLiveService = animalLiveService;
        this.newDayService = newDayService;
        this.generalLatch = genLatch;
    }

    @Override
    public void run() {

        if (dayCount == Simulation.getDAYS()) {
            animalLiveService.shutdown();
            newDayService.shutdown();
        }

        CountDownLatch localLatch = new CountDownLatch(Island.SIZE);

        for (int i = 0; i < Island.SIZE; i++) {
            Cell cell = Island.getCell(i);

            animalLiveService.submit(cell);

            localLatch.countDown();
        }

        try {
            localLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("ДЕНЬ #" + ++dayCount);
        System.out.println(Island.getStatus());
        System.out.println("-".repeat(50));

        localLatch = new CountDownLatch(Island.SIZE);

        for (int i = 0; i < Island.SIZE; i++) {
            Cell cell = Island.getCell(i);

            for (Animal animal : cell.getPopulationQueue()) {
                animal.becomeFreshAgain();
            }

            cell.fillWithPlants(Plant.getGrowSpeed());

            localLatch.countDown();
        }

        try {
            localLatch.await();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        generalLatch.countDown();
    }
}
