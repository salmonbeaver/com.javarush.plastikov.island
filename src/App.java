import settings.Data;
import entity.*;

import java.util.concurrent.*;

public class App {
    // ПО ОТДЕЛЬНОСТИ ВСЕ МЕТОДЫ РАБОТАЮТ ПРАВИЛЬНО (ВРОДЕ БЫ),
    // НО В СОВОКУПНОСТИ ПОЛУЧАЮТСЯ СОМНИТЕЛЬНЫЕ ЦИФРЫ
    // ПРИ ВЫВОДЕ СТАТИСТИКИ В КОНСОЛЬ
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

            while (Data.DAYS_TO_OBSERVE > 0) {
                dayCount++;

                for (int i = 0; i < Island.SIZE; i++) {
                    animalLiveService.execute(Island.getCell(i));
                }

                for (int i = 0; i < Island.SIZE; i++) {
                    scheduledPlantService.schedule(new Plant(), 0, TimeUnit.SECONDS);
                }

                System.out.println("ДЕНЬ # " + dayCount);
                System.out.println(Island.getStatus());
                System.out.println("-".repeat(210));

                Data.DAYS_TO_OBSERVE--;

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
