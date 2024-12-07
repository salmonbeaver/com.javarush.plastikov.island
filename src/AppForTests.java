import entity.Animal;
import entity.Cell;
import entity.Island;
import entity.Plant;
import settings.Data;

public class AppForTests {

    public static void main(String[] args){
        Data.init();
        Island.init();

        Cell testCell = Island.getCell(0);

        for (int i = 0; i < 11; i++) {
            System.out.println("DAY #" + i);
            System.out.println(testCell.getStatus());
            Island.setNewDay();
            int plantCount = testCell.getPlantList().size();

            for (Animal animal : testCell.getPopulationQueue()) {
                animal.eat();
                animal.reduceSatiety();

                if (animal.getActualSatiety() < 0) {
                    animal.die();
                }

                if (animal.isHungry()) {
                    if (animal.eat()) {
                        animal.reproduce();
                    }
                } else {
                    animal.reproduce();
                }
            }

            System.out.println(" - " + Data.getPictureByID(Plant.getID()) + (plantCount - testCell.getPlantList().size()));
            System.out.println("-".repeat(210));

            int plantNumber = Plant.getCapacity() / 4;

            testCell.fillWithPlants(plantNumber);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
