package entity;

import settings.Data;

import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class Island {

    public static final int WIDTH = 100;
    public static final int HEIGHT = 20;
    public static final int SIZE = WIDTH * HEIGHT;
    private static final List<Cell> CELL_LIST = new ArrayList<>(SIZE);

    public static void init() {
        for (int i = 0; i < SIZE; i++) {
            CountDownLatch cdl = new CountDownLatch(SIZE);
            Cell cell = new Cell(cdl);
            CELL_LIST.add(cell);
        }
    }

    public static Cell getCell(int i) {
        return CELL_LIST.get(i);
    }

    public static String getStatus() {
        int animalCount = 0;
        int plantCount = 0;

        Map<Integer, Integer> islandAnimalCountMap = new HashMap<>();

        // инициализация мапы ID животных как ключей и 0 в кач-ве значения
        for (int i = 0; i < 15; i++) {
            islandAnimalCountMap.put(i, 0);
        }

        for (Cell cell : CELL_LIST) {

            cell.refreshAnimalCountMap();

            for (Map.Entry<Integer, Integer> cellEntry : cell.getAnimalCountMap().entrySet()) {
                int oldValue = islandAnimalCountMap.get(cellEntry.getKey());
                int newValue = oldValue + cellEntry.getValue();
                islandAnimalCountMap.put(cellEntry.getKey(), newValue);
            }

            animalCount = animalCount + cell.getPopulationQueue().size();
            plantCount = plantCount + cell.getPlantList().size();
        }

        StringBuilder status = new StringBuilder();

        for (Map.Entry<Integer, Integer> entry : islandAnimalCountMap.entrySet()) {
            String formattedSpecificAnimalCount = NumberFormat.getInstance(Locale.US).format(entry.getValue());

            status.append(Data.getPictureByID(entry.getKey()))
                    .append(" : ")
                    .append(formattedSpecificAnimalCount)
                    .append(" | ");
        }

        String formattedMaxPlantCapacity = NumberFormat.getInstance(Locale.US).format((long) Cell.MAX_PLANT_CAPACITY * SIZE);
        String formattedPlantCount = NumberFormat.getInstance(Locale.US).format(plantCount);

        status.append("\n")
                .append(Data.getPictureByID(Plant.getID()))
                .append(" : ")
                .append(formattedPlantCount)
                .append(" из ")
                .append(formattedMaxPlantCapacity)
                .append(" возможных");

        String formattedMaxAnimalCapacity = NumberFormat.getInstance(Locale.US).format((long) Cell.MAX_ANIMAL_CAPACITY * SIZE);
        String formattedAnimalCount = NumberFormat.getInstance(Locale.US).format(animalCount);

        String title = "Всего животных на острове : " + formattedAnimalCount + " из " + formattedMaxAnimalCapacity + " возможных\n";

        return title + status;
    }

    public static void setNewDay() {

        for (Cell cell : CELL_LIST) {

            for (Animal animal : cell.getPopulationQueue()) {
                animal.becomeFreshAgain();
            }
        }
    }
}
