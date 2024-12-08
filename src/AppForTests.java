import entity.Animal;
import entity.Cell;
import entity.Island;
import entity.Plant;
import entity.herbivore.Caterpillar;
import settings.Data;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppForTests {

    public static void main(String[] args) throws InterruptedException {
        Data.init();
        Island.init();

        Simulation simulation = new Simulation();
        simulation.go();
    }
}


//import entity.Animal;
//import entity.Cell;
//import entity.Island;
//import entity.Plant;
//import settings.Data;
//
//public class AppForTests {
//
//    public static void main(String[] args){
//        Data.init();
//        Island.init();
/// /for (int i = 0; i < Data.OBSERVE_DAYS; i++)
//        int i = 0;
//        while (true){
//
//            Island.setNewDay();
//            System.out.println("DAY #" + i);
//            System.out.println(Island.getStatus());
//
//
//            for (int j = 0; j < Island.SIZE; j++) {
//
//                Cell currentCell = Island.getCell(j);
//
//                for (Animal animal : currentCell.getPopulationQueue()) {
//
//                    if (animal.isHungry()) {
//                        animal.eat();
//                    }
//
//                    animal.reduceSatiety();
//
//                    if (animal.getActualSatiety() < 0) {
//                        animal.die();
//                    }
//
////                    animal.reproduce();
//
//                }
//
//                int plantNumber = Plant.getCapacity() / 4;
//
////                currentCell.fillWithPlants(plantNumber);
//            }
//
//            System.out.println("-".repeat(210));
//            i++;
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}
