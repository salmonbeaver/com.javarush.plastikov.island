import data.Animals;
import data.Game;
import entity.Animal;
import entity.Bear;
import entity.Herbivore;
import entity.Predator;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Cell {

    private static int capacity = Game.getOneCellCapacity(); // useless?
    public Map<Animal, Double> populationMap = new HashMap<>(capacity);
    public static Map<Class<? extends Animal>, Integer> cellCapacity = Game.getOneCellCapacityMap();
//    public ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(6);

//    public Cell() {
//    конструктор
//    }


    public void populate() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Random random = new Random();

        for (Map.Entry<Class<? extends Animal>, Integer> entry : cellCapacity.entrySet()) {
            int numberOfAnimal = random.nextInt(entry.getValue()) + 1;
            Class<? extends Animal> currentAnimalType = entry.getKey();
            double actualSatiety = Game.getActualSatiety(currentAnimalType);

            for (int i = 0; i < numberOfAnimal; i++) {
                populationMap.put(currentAnimalType.newInstance(), actualSatiety);
            }

        }

    }

}
