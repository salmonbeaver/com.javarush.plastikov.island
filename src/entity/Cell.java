package entity;

import lombok.Getter;
import settings.Data;
import settings.MyRandom;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Cell implements Runnable {

    private static int cellCount = 0;
    private final int id;
    public static final int MAX_ANIMAL_CAPACITY = Data.getOneCellMaxCapacity();
    public static final int MAX_PLANT_CAPACITY = Plant.getCapacity();
    private static final Map<Integer, Integer> SPECIFIC_ANIMAL_CAPACITY_MAP = Data.getOneCellCapacityMap(); // ID животного : лимит в клетке
    @Getter
    private ConcurrentLinkedQueue<Animal> populationQueue = new ConcurrentLinkedQueue<>(); // коллекция всех животных в клетке
    @Getter
    private List<Plant> plantList = new ArrayList<>(); // коллекция всех растений в клетке
    @Getter
    private Map<Integer, Integer> animalCountMap = new HashMap<>(); // Животное : кол-во в клетке

    public Cell() {
        id = cellCount++;
        fillWithPlants(Plant.getGrowSpeed());
        populate();
        refreshAnimalCountMap();
    }

    // Стартовое заполнение клетки животными
    public void populate() {

        for (Map.Entry<Integer, Integer> entry : SPECIFIC_ANIMAL_CAPACITY_MAP.entrySet()) {
            int numberOfAnimals = MyRandom.RANDOM.nextInt(entry.getValue() - 1) + 2; // выбираем случайное число животных (2-MAX_CAPACITY)
            Class<? extends Animal> animalType = Data.getClassByID(entry.getKey()); // класс текущего животного

            // Заполнение листа животными
            for (int i = 0; i < numberOfAnimals; i++) {
                Animal animal = Animal.createAnimalByReflection(animalType);
                animal.setCellID(id);
                populationQueue.add(animal);
            }
        }
    }

    // Перепись населения (обновление данных по клетке)
    public void refreshAnimalCountMap() {

        for (int i = 0; i < 15; i++) {
            animalCountMap.put(i, 0);
        }

        for (Animal animal : populationQueue) {
            int oldValue = animalCountMap.get(animal.getId());
            int newValue = oldValue + 1;

            animalCountMap.put(animal.getId(), newValue);
        }
    }

    // Получение списка ID животных
    public List<Integer> getAnimals() {
        List<Integer> animals = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : animalCountMap.entrySet()) {
            animals.add(entry.getKey());
        }

        return animals;
    }

    // Проверка на переполненность
    public boolean isCrowded(Animal checkedAnimal) {
        int animalCount = 0;

        for (Animal animal : populationQueue) {
            if (checkedAnimal.getId() == animal.getId()) {
                animalCount++;
            }

        }

        return (animalCount == SPECIFIC_ANIMAL_CAPACITY_MAP.get(checkedAnimal.getId()));
    }

    // Сводная информация по клетке
    public String getStatus() {
        String title = "Всего животных в клетке #" + id + ": " + getPopulationQueue().size() + "\n";
        StringBuilder status = new StringBuilder();

        refreshAnimalCountMap();

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

    // Заполнение клетки растениями
    public void fillWithPlants(int plantNumber) {

        for (int i = 0; i < plantNumber; i++) {

            if (plantList.size() == Plant.getCapacity()) {
                return;
            }

            plantList.add(new Plant());
        }
    }

    @Override
    public void run() {

        for (Animal animal : getPopulationQueue()) {

            animal.reduceSatiety();

            if (animal.getActualSatiety() < 0) {
                getPopulationQueue().remove();

                continue;
            }

            if (animal.isHungry()) {
                if (animal.eat()) {
                    animal.reproduce();
                }
            } else {
                animal.reproduce();
            }

            animal.move();
        }
    }
}
