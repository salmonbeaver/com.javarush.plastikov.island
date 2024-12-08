import entity.Cell;
import entity.Island;
import settings.Data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simulation {

    public void go() throws InterruptedException {



        ExecutorService animalLiveService = Executors.newFixedThreadPool(Data.CORES);

        for (int i = 1; i < 10; i++) {

            Island.setNewDay();

            for (int j = 0; j < Island.SIZE; j++) {
                Cell currentCell = Island.getCell(j);

                animalLiveService.execute(currentCell);

                currentCell.LATCH.await();
            }

            System.out.println("-".repeat(210));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("DAY #" + i);
            System.out.println(Island.getStatus());


        }
    }

}
