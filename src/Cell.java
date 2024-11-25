import data.Game;
import entity.Animal;
import entity.Plant;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Cell {

    Random random = new Random();
    private static int maxCapacitycapacity = Game.getOneCellMaxCapacity(); // useless?
    public Map<Animal, Double> populationMap = new HashMap<>(maxCapacitycapacity);
    private static Map<Integer, Integer> specificAnimalCapacityMap = Game.getOneCellCapacityMap();
    public Map <Animal, Integer> animalCountMap = new HashMap<>();
    private List<Plant> plantList = new ArrayList<>();

    public void populate() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        for (Map.Entry<Integer, Integer> entry : specificAnimalCapacityMap.entrySet()) {
            int numberOfAnimals = random.nextInt(entry.getValue() - 1) + 2; // выбираем случайное число животных (от двух до макс возможного их числа)
            int id = entry.getKey();
            double startSatiety = Game.getActualSatiety(id); // получаем стартовую сытость животного
            Class<? extends Animal> currentAnimalType = Game.getClassByID(entry.getKey());
            int countOfOneType = 0;
            
            // Заполнение мапы животными
            for (int i = 0; i < numberOfAnimals; i++) {
                populationMap.put(currentAnimalType.newInstance(), startSatiety);
                countOfOneType++;
            }

            // Количество животных каждого типа
            animalCountMap.put(currentAnimalType.newInstance(), countOfOneType); //

            int numberOfPlants = random.nextInt(Plant.getCapacity()) + 1;

            // Заполнение листа растениями
            for (int i = 0; i < numberOfPlants; i++) {
                plantList.add(new Plant());
            }
        }

    }

    public void Live() {
        while (true) {
            for (Map.Entry<Animal, Double> entry : populationMap.entrySet()) {
//                if (entry.getKey().eat()==;
                double afterHungry = entry.getKey().getActualSatiety() - entry.getKey().getMaxSatiety() / 7;
                entry.getKey().setActualSatiety(afterHungry);
            }
        }
    }
}
