package entity;

import settings.Data;

import java.text.NumberFormat;
import java.util.*;

public class Island {

    public static final int WIDTH = 100;
    public static final int HEIGHT = 20;
    public static final int SIZE = WIDTH * HEIGHT;
    private static final List<Cell> CELL_LIST = new ArrayList<>(SIZE);
    public static final Map<Integer, Integer> ISLAND_ANIMAL_COUNTMAP = new HashMap<>();

    public static void init() {

        for (int i = 0; i < 15; i++) {
            ISLAND_ANIMAL_COUNTMAP.put(i, 0);
        }

        for (int i = 0; i < SIZE; i++) {
            Cell cell = new Cell();
            CELL_LIST.add(cell);
        }

    }

    public static Cell getCell(int i) {
        return CELL_LIST.get(i);
    }

    // Сводная информация по острову
    public static String getStatus() {
        int plantCount = 0;
        int animalCount = 0;

        refreshAnimalCountMap();

        for (Cell cell : CELL_LIST) {
            plantCount = plantCount + cell.getPlantList().size();
            animalCount = animalCount + cell.getPopulationQueue().size();
        }

        StringBuilder status = new StringBuilder();

        for (Map.Entry<Integer, Integer> entry : ISLAND_ANIMAL_COUNTMAP.entrySet()) {

            int specificAnimalCount = entry.getValue();
            String formattedSpecificAnimalCount = NumberFormat.getInstance(Locale.US).format(specificAnimalCount);

            status.append(Data.getPictureByID(entry.getKey()))
                    .append(" : ")
                    .append(formattedSpecificAnimalCount)
                    .append(" | ");
        }

        int maxPlantCapacity = Cell.MAX_PLANT_CAPACITY * SIZE;
        String formattedMaxPlantCapacity = NumberFormat.getInstance(Locale.US).format(maxPlantCapacity);
        String formattedPlantCount = NumberFormat.getInstance(Locale.US).format(plantCount);

        status.append("\n")
                .append(Data.getPictureByID(Plant.getID()))
                .append(" : ")
                .append(formattedPlantCount)
                .append(" / ")
                .append(formattedMaxPlantCapacity);

        int maxAnimalCapacity = Cell.MAX_ANIMAL_CAPACITY * SIZE;
        String formattedMaxAnimalCapacity = NumberFormat.getInstance(Locale.US).format(maxAnimalCapacity);
        String formattedAnimalCount = NumberFormat.getInstance(Locale.US).format(animalCount);

        String title = "Всего животных на острове : " + formattedAnimalCount + " / " + formattedMaxAnimalCapacity + "\n";

        return title + status;
    }

    private static void refreshAnimalCountMap() {

        for (int i = 0; i < 15; i++) {
            ISLAND_ANIMAL_COUNTMAP.put(i, 0);
        }

        for (int i = 0; i < Island.SIZE; i++) {
            Cell cell = Island.getCell(i);

            for (int j = 0; j < 15; j++) {
                cell.getAnimalCountMap().put(j, 0);
            }

            for (Animal animal : cell.getPopulationQueue()) {
                int oldValue = cell.getAnimalCountMap().get(animal.getId());
                int newValue = oldValue + 1;

                cell.getAnimalCountMap().put(animal.getId(), newValue);

                int islandOldValue = ISLAND_ANIMAL_COUNTMAP.get(animal.getId());
                int islandNewValue = islandOldValue + 1;
                ISLAND_ANIMAL_COUNTMAP.put(animal.getId(), islandNewValue);
            }
        }
    }
}
