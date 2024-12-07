package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Plant implements Runnable{

    @Getter private static final int ID = 15;
    @Getter private static int capacity = 200;
    @Getter private static double weight = 1;
    @Setter private int cellID;
    @Getter private static int growSpeed = capacity;

    public static void die(int cellID) {
        Cell cell = Island.getCell(cellID);

        if (!cell.getPlantList().isEmpty()) {
            cell.getPlantList().removeLast();
        }
    }

    @Override
    public void run() {

//        for (int i = 0; i < Plant.getGrowSpeed(); i++) {
//            Island.getCell(cellID).fillWithPlants(getCapacity());
//        }

    }
}
