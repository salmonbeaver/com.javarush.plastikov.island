package entity;

import settings.Data;

import java.util.*;

public class Cell {

    private static final Random RANDOM = new Random();
    private static int cellCount = 0;
    private final int ID;
    private static final int MAX_ANIMAL_CAPACITY = Data.getOneCellMaxCapacity();
    private static final int MAX_PLANT_CAPACITY = Plant.getCapacity();
    private static final Map<Integer, Integer> SPECIFIC_ANIMAL_CAPACITY_MAP = Data.getOneCellCapacityMap();
    public List<Animal> populationList = new ArrayList<>(MAX_ANIMAL_CAPACITY);
    public List<Plant> plantList = new ArrayList<>(MAX_PLANT_CAPACITY);

    public Map <Animal, Integer> animalCountMap = new HashMap<>();

    public Cell() {
        ID = cellCount++;
    }

    public void populate() throws InstantiationException, IllegalAccessException {

        for (Map.Entry<Integer, Integer> entry : SPECIFIC_ANIMAL_CAPACITY_MAP.entrySet()) {
            int numberOfAnimals = RANDOM.nextInt(entry.getValue() - 1) + 2; // выбираем случайное число животных (от двух до макс возможного их числа)
            Class<? extends Animal> currentAnimalType = Data.getClassByID(entry.getKey()); // класс текущего животного
            int countOfOneType = 0; // счетчик животных конкретного типа
            
            // Заполнение листа животными
            for (int i = 0; i < numberOfAnimals; i++) {
                Animal animal = currentAnimalType.newInstance();
                animal.setCellNumber(ID);
                populationList.add(animal);
                countOfOneType++;
            }

            // Количество животных каждого типа
            animalCountMap.put(currentAnimalType.newInstance(), countOfOneType); //

            int numberOfPlants = RANDOM.nextInt(Plant.getCapacity()) + 1;

            // Заполнение листа растениями
            for (int i = 0; i < numberOfPlants; i++) {
                plantList.add(new Plant());
            }
        }

    }

    public void live() {
        while (true) {
            for (Animal animal : populationList) {
                animal.eat();
            }
        }
    }

    public Cell getCell() {
        return null;
    }

}
