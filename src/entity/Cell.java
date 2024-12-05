package entity;

import lombok.Getter;
import settings.Data;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

public class Cell implements Runnable {

    public CountDownLatch latch;

    private static final Random RANDOM = new Random();
    private static int cellCount = 0;
    private final int ID;
    public static final int MAX_ANIMAL_CAPACITY = Data.getOneCellMaxCapacity();
    public static final int MAX_PLANT_CAPACITY = Plant.getCapacity();
    private static final Map<Integer, Integer> SPECIFIC_ANIMAL_CAPACITY_MAP = Data.getOneCellCapacityMap(); // ID животного : макс число в клетке
    @Getter
    private List<Animal> populationList = new CopyOnWriteArrayList<>();
    @Getter
    private List<Plant> plantList = new CopyOnWriteArrayList<>();
    @Getter
    private Map<Integer, Integer> animalCountMap = new HashMap<>(); // Животное : кол-во в клетке

    public Cell(CountDownLatch c) {
        ID = cellCount++;
        fillWithPlants();
        populate();
        refreshAnimalCountMap();
        latch = c;
    }


    // Стартовое заполнение list'a животными
    public void populate() {

        for (Map.Entry<Integer, Integer> entry : SPECIFIC_ANIMAL_CAPACITY_MAP.entrySet()) {
            int numberOfAnimals = RANDOM.nextInt(entry.getValue() - 1) + 2; // выбираем случайное число животных (от двух до макс возможного их числа)
            Class<? extends Animal> currentAnimalType = Data.getClassByID(entry.getKey()); // класс текущего животного

            // Заполнение листа животными
            for (int i = 0; i < numberOfAnimals; i++) {
                Animal animal = Animal.createAnimalByReflection(currentAnimalType);
                animal.setCellID(ID);
                populationList.add(animal);
            }
        }
    }

    public void refreshAnimalCountMap() {

        for (int i = 0; i < 15; i++) {
            animalCountMap.put(i, 0);
        }

        for (Animal animal : populationList) {
            int oldValue = animalCountMap.get(animal.getId());
            int newValue = oldValue + 1;

            animalCountMap.put(animal.getId(), newValue);
        }
    }

    public List<Integer> getAnimals() {
        List<Integer> animals = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : animalCountMap.entrySet()) {
            animals.add(entry.getKey());
        }

        return animals;
    }

    // Стартовое заполнение листа растениями
    private void fillWithPlants() {

        int numberOfPlants = RANDOM.nextInt(Plant.getCapacity()) + 1;

        for (int i = 0; i < numberOfPlants; i++) {
            growPlant();
        }
    }

    // Проверка на переполненность
    public boolean isCrowded(Animal checkedAnimal) {
        int animalCount = 0;

        for (Animal animal : populationList) {
            if (checkedAnimal.getId() == animal.getId()) {
                animalCount++;
            }

        }

        return (animalCount == SPECIFIC_ANIMAL_CAPACITY_MAP.get(checkedAnimal.getId()));
    }

    public String getStatus() {
        String title = "Всего животных в клетке #" + ID + ": " + getPopulationList().size() + "\n";
        StringBuilder status = new StringBuilder();

        for (Map.Entry<Integer, Integer> entry : animalCountMap.entrySet()) {
            status.append(Data.getPictureByID(entry.getKey()))
                    .append(" : ")
                    .append(entry.getValue())
                    .append(" | ");
        }
        status.append("\n")
                .append(Data.getPictureByID(Plant.getID()))
                .append(" : ")
                .append(plantList.size());

        return title + status;
    }

    public void growPlant() {
        Plant plant = new Plant();

        if (plantList.size() == Plant.getCapacity()) {
            return;
        }

        plant.setCellID(ID);
        plantList.add(plant);
    }

    @Override
    public void run() {

        for (Animal animal : populationList) {

            if (animal.isTired) {
                continue;
            }

            // Если животное поело или уже сытое - оно размножается
            if (animal.isHungry()) {
                if (animal.eat()) {
                    animal.reproduce();
                }
            } else {
                animal.reproduce();
            }

            animal.move(); // Затем двигается
            animal.reduceSatiety(); // и сжигает калории

            // Если сытость = 0 - животное умирает
            if (animal.getActualSatiety() <= 0) {
                animal.die();
            }

            latch.countDown();
        }
    }
}
