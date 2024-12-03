package entity;

import settings.Data;

import java.util.*;

public class Cell {

    private static final Random RANDOM = new Random();
    private static int cellCount = 0;
    private final int ID;
    private static final int MAX_ANIMAL_CAPACITY = Data.getOneCellMaxCapacity();
    private static final int MAX_PLANT_CAPACITY = Plant.getCapacity();
    private static final Map<Integer, Integer> SPECIFIC_ANIMAL_CAPACITY_MAP = Data.getOneCellCapacityMap(); // ID животного : макс число в клетке
    public List<Animal> populationList = new ArrayList<>(MAX_ANIMAL_CAPACITY);
    public final List<Plant> plantList = new ArrayList<>(MAX_PLANT_CAPACITY);
    private static final Map <Animal, Integer> animalCountMap = new HashMap<>(); // Животное : кол-во в клетке

    public Cell() {
        ID = cellCount++;
        fillByPlant();
    }

    // Стартовое заполнение клетки (мапы) животными
    public void populate() throws InstantiationException, IllegalAccessException {

        for (Map.Entry<Integer, Integer> entry : SPECIFIC_ANIMAL_CAPACITY_MAP.entrySet()) {
            int numberOfAnimals = RANDOM.nextInt(entry.getValue() - 1) + 2; // выбираем случайное число животных (от двух до макс возможного их числа)
            Class<? extends Animal> currentAnimalType = Data.getClassByID(entry.getKey()); // класс текущего животного
            int countOfOneType = 0; // счетчик животных конкретного типа
            
            // Заполнение листа животными
            for (int i = 0; i < numberOfAnimals; i++) {
                Animal animal = Animal.createAnimalByReflection(currentAnimalType);
                animal.setCellID(ID);
                populationList.add(animal);
                countOfOneType++;
            }

            // Количество животных каждого типа
            animalCountMap.put(currentAnimalType.newInstance(), countOfOneType);
        }

    }

    public List<Animal> getAnimals (){
        List<Animal> animals = new ArrayList<>();

        for (Map.Entry<Animal, Integer> entry : animalCountMap.entrySet()) {
            animals.add(entry.getKey());
        }

        return animals;
    }

    public int getAnimalCapacity(int id) {
        return SPECIFIC_ANIMAL_CAPACITY_MAP.get(id);
    }

    // Стартовое заполнение листа растениями
    private void fillByPlant() {

        int numberOfPlants = RANDOM.nextInt(Plant.getCapacity()) + 1;
        for (int i = 0; i < numberOfPlants; i++) {
            plantList.add(new Plant());
        }

    }

    // Проверка на переполненность
    public boolean isCrowded(List<Animal> animalList, Animal checkedAnimal) {
        int animalCount = 0;

        for (Animal animal : animalList) {
            if (checkedAnimal.getIDByReflection(checkedAnimal) == animal.getIDByReflection(animal)) {
                animalCount++;
            }

        }

        return animalCount == SPECIFIC_ANIMAL_CAPACITY_MAP.get(checkedAnimal.getIDByReflection(checkedAnimal));
    }

    // Обновление населения
    public void refreshPopulation(List<Animal> newPopulationList) {
        populationList = newPopulationList;
    }

//    @Override
//    public void run() {
//
//        for (Animal animal : populationList) {
//            animal.eat();
//        }
//    }
}
