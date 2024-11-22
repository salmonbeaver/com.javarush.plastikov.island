package entity;

import data.Animals;

import java.util.HashMap;
import java.util.Map;

public class Settings {

    public static final Map<Animals, Double> weightMap = new HashMap<>();
    public static final Map<Animals, Integer> speedMap = new HashMap<>();
    public static final Map<Animals, Double> maxSatietyMap = new HashMap<>();
    public static Map<Animals, Double> actualSatietyMap = new HashMap<>();
    public static Map<Animals, Map<Animals, Integer>> eatenPoolMap = new HashMap<>();

    public static void init() {
        weightMap.put(Animals.BEAR, 500.0);
        weightMap.put(Animals.BOAR, 400.0);
        weightMap.put(Animals.BUFFALO, 700.0);
        weightMap.put(Animals.CATERPILLAR, 0.01);
        weightMap.put(Animals.DEER, 300.0);
        weightMap.put(Animals.DUCK, 1.0);
        weightMap.put(Animals.EAGLE, 6.0);
        weightMap.put(Animals.FOX, 8.0);
        weightMap.put(Animals.GOAT, 60.0);
        weightMap.put(Animals.HORSE, 400.0);
        weightMap.put(Animals.MOUSE, 0.05);
        weightMap.put(Animals.RABBIT, 2.0);
        weightMap.put(Animals.SHEEP, 70.0);
        weightMap.put(Animals.SNAKE, 15.0);
        weightMap.put(Animals.WOLF, 50.0);

        speedMap.put(Animals.BEAR, 2);
        speedMap.put(Animals.BOAR, 2);
        speedMap.put(Animals.BUFFALO, 3);
        speedMap.put(Animals.CATERPILLAR, 0);
        speedMap.put(Animals.DEER, 4);
        speedMap.put(Animals.DUCK, 4);
        speedMap.put(Animals.EAGLE, 3);
        speedMap.put(Animals.FOX, 2);
        speedMap.put(Animals.GOAT, 3);
        speedMap.put(Animals.HORSE, 4);
        speedMap.put(Animals.MOUSE, 1);
        speedMap.put(Animals.RABBIT, 2);
        speedMap.put(Animals.SHEEP, 3);
        speedMap.put(Animals.SNAKE, 1);
        speedMap.put(Animals.WOLF, 3);

        maxSatietyMap.put(Animals.BEAR, 80.0);
        maxSatietyMap.put(Animals.BOAR, 50.0);
        maxSatietyMap.put(Animals.BUFFALO, 100.0);
        maxSatietyMap.put(Animals.CATERPILLAR, 0.0);
        maxSatietyMap.put(Animals.DEER, 50.0);
        maxSatietyMap.put(Animals.DUCK, 0.015);
        maxSatietyMap.put(Animals.EAGLE, 1.0);
        maxSatietyMap.put(Animals.FOX, 2.0);
        maxSatietyMap.put(Animals.GOAT, 10.0);
        maxSatietyMap.put(Animals.HORSE, 60.0);
        maxSatietyMap.put(Animals.MOUSE, 0.01);
        maxSatietyMap.put(Animals.RABBIT, 0.45);
        maxSatietyMap.put(Animals.SHEEP, 15.0);
        maxSatietyMap.put(Animals.SNAKE, 3.0);
        maxSatietyMap.put(Animals.WOLF, 8.0);

        actualSatietyMap.put(Animals.BEAR, maxSatietyMap.get(Animals.BEAR));
        actualSatietyMap.put(Animals.BOAR, maxSatietyMap.get(Animals.BOAR));
        actualSatietyMap.put(Animals.BUFFALO, maxSatietyMap.get(Animals.BUFFALO));
        actualSatietyMap.put(Animals.CATERPILLAR, maxSatietyMap.get(Animals.CATERPILLAR));
        actualSatietyMap.put(Animals.DEER, maxSatietyMap.get(Animals.DEER));
        actualSatietyMap.put(Animals.DUCK, maxSatietyMap.get(Animals.DUCK));
        actualSatietyMap.put(Animals.EAGLE, maxSatietyMap.get(Animals.EAGLE));
        actualSatietyMap.put(Animals.FOX, maxSatietyMap.get(Animals.FOX));
        actualSatietyMap.put(Animals.GOAT, maxSatietyMap.get(Animals.GOAT));
        actualSatietyMap.put(Animals.HORSE, maxSatietyMap.get(Animals.HORSE));
        actualSatietyMap.put(Animals.MOUSE, maxSatietyMap.get(Animals.MOUSE));
        actualSatietyMap.put(Animals.RABBIT, maxSatietyMap.get(Animals.RABBIT));
        actualSatietyMap.put(Animals.SHEEP, maxSatietyMap.get(Animals.SHEEP));
        actualSatietyMap.put(Animals.SNAKE, maxSatietyMap.get(Animals.SNAKE));
        actualSatietyMap.put(Animals.WOLF, maxSatietyMap.get(Animals.WOLF));


        eatenPoolMap.put(Animals.BEAR, Map.of(Animals.RABBIT, 70
                , Animals.MOUSE, 90
                , Animals.DUCK, 60
                , Animals.CATERPILLAR, 40));
        eatenPoolMap.put(Animals.BOAR, Map.of(Animals.PLANT, 100
                , Animals.MOUSE, 90
                , Animals.CATERPILLAR, 90));
        eatenPoolMap.put(Animals.BUFFALO, Map.of(Animals.PLANT, 100));
        eatenPoolMap.put(Animals.CATERPILLAR, Map.of(Animals.PLANT, 100));
        eatenPoolMap.put(Animals.DEER, Map.of(Animals.PLANT, 100));
        eatenPoolMap.put(Animals.DUCK, Map.of(Animals.PLANT, 100
                , Animals.CATERPILLAR, 90));
        eatenPoolMap.put(Animals.EAGLE, Map.of(Animals.FOX, 10
                , Animals.RABBIT, 90
                , Animals.MOUSE, 90
                , Animals.DUCK, 80));
        eatenPoolMap.put(Animals.FOX, Map.of(Animals.SNAKE, 80
                , Animals.HORSE, 40
                , Animals.DEER, 80
                , Animals.RABBIT, 80
                , Animals.MOUSE, 90
                , Animals.GOAT, 70
                , Animals.SHEEP, 70
                , Animals.BOAR, 50
                , Animals.BUFFALO, 20
                , Animals.DUCK, 10));
        eatenPoolMap.put(Animals.GOAT, Map.of(Animals.PLANT, 100));
        eatenPoolMap.put(Animals.HORSE, Map.of(Animals.PLANT, 100));
        eatenPoolMap.put(Animals.MOUSE, Map.of(Animals.PLANT, 100
                , Animals.MOUSE, 90
                , Animals.CATERPILLAR, 90));
        eatenPoolMap.put(Animals.RABBIT, Map.of(Animals.PLANT, 100));
        eatenPoolMap.put(Animals.SHEEP, Map.of(Animals.PLANT, 100));
        eatenPoolMap.put(Animals.SNAKE, Map.of(Animals.FOX, 15
                , Animals.RABBIT, 20
                , Animals.MOUSE, 40
                , Animals.DUCK, 40));
        eatenPoolMap.put(Animals.WOLF, Map.of(Animals.HORSE, 10
                , Animals.DEER, 15
                , Animals.RABBIT, 60
                , Animals.MOUSE, 80
                , Animals.GOAT, 60
                , Animals.SHEEP, 70
                , Animals.BOAR, 15
                , Animals.BUFFALO, 10
                , Animals.DUCK, 40));
    }

    public static double getWeight(Animals name) {
        return weightMap.get(name);
    }

    public static int getSpeed(Animals name) {
        return speedMap.get(name);
    }

    public static double getMaxSatiety(Animals name) {
        return maxSatietyMap.get(name);
    }

    public static double getActualSatiety(Animals name) {
        return actualSatietyMap.get(name);
    }

    public static Map<Animals, Integer> getEatenPool(Animals name) {
        return eatenPoolMap.get(name);
    }

    private Settings() {
        throw new IllegalStateException("Utility class");
    }
}
