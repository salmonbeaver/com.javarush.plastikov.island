package data;

import entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    public static final List<Integer> allIDList = List.of(Bear.getId(), Boar.getId(), Buffalo.getId()
            , Caterpillar.getId(), Deer.getId(), Duck.getId(), Eagle.getId()
            , Fox.getId(), Goat.getId(), Horse.getId(), Mouse.getId(), Rabbit.getId()
            , Sheep.getId(), Snake.getId(), Wolf.getId());
    protected static final Map<Integer, Double> weightMap = new HashMap<>(); // Коллекция весов
    protected static final Map<Integer, Integer> speedMap = new HashMap<>(); // Коллекция скоростей
    protected static final Map<Integer, Double> maxSatietyMap = new HashMap<>(); // Коллекция макс сытостей
    protected static Map<Integer, Double> actualSatietyMap = new HashMap<>(); // Коллекция актуальных сытостей
    protected static Map<Integer, Map<Integer, Integer>> foodPoolMap = new HashMap<>(); // Коллекция кто кого ест
    protected static Map<Integer, String> picturesMap = new HashMap<>(); // Коллекция эмодзи
    protected static Map<Class<? extends Animal>, Integer> oneCellCapacityMap = new HashMap<>(); // Коллекция вместимостей в одной клетке

    public static void init() {
        weightMap.put(Bear.getId(), 500.0);
        weightMap.put(Boar.getId(), 400.0);
        weightMap.put(Buffalo.getId(), 700.0);
        weightMap.put(Caterpillar.getId(), 0.01);
        weightMap.put(Deer.getId(), 300.0);
        weightMap.put(Duck.getId(), 1.0);
        weightMap.put(Eagle.getId(), 6.0);
        weightMap.put(Fox.getId(), 8.0);
        weightMap.put(Goat.getId(), 60.0);
        weightMap.put(Horse.getId(), 400.0);
        weightMap.put(Mouse.getId(), 0.05);
        weightMap.put(Rabbit.getId(), 2.0);
        weightMap.put(Sheep.getId(), 70.0);
        weightMap.put(Snake.getId(), 15.0);
        weightMap.put(Wolf.getId(), 50.0);

        speedMap.put(Bear.getId(), 2);
        speedMap.put(Boar.getId(), 2);
        speedMap.put(Buffalo.getId(), 3);
        speedMap.put(Caterpillar.getId(), 0);
        speedMap.put(Deer.getId(), 4);
        speedMap.put(Duck.getId(), 4);
        speedMap.put(Eagle.getId(), 3);
        speedMap.put(Fox.getId(), 2);
        speedMap.put(Goat.getId(), 3);
        speedMap.put(Horse.getId(), 4);
        speedMap.put(Mouse.getId(), 1);
        speedMap.put(Rabbit.getId(), 2);
        speedMap.put(Sheep.getId(), 3);
        speedMap.put(Snake.getId(), 1);
        speedMap.put(Wolf.getId(), 3);

        maxSatietyMap.put(Bear.getId(), 80.0);
        maxSatietyMap.put(Boar.getId(), 50.0);
        maxSatietyMap.put(Buffalo.getId(), 100.0);
        maxSatietyMap.put(Caterpillar.getId(), 0.0);
        maxSatietyMap.put(Deer.getId(), 50.0);
        maxSatietyMap.put(Duck.getId(), 0.015);
        maxSatietyMap.put(Eagle.getId(), 1.0);
        maxSatietyMap.put(Fox.getId(), 2.0);
        maxSatietyMap.put(Goat.getId(), 10.0);
        maxSatietyMap.put(Horse.getId(), 60.0);
        maxSatietyMap.put(Mouse.getId(), 0.01);
        maxSatietyMap.put(Rabbit.getId(), 0.45);
        maxSatietyMap.put(Sheep.getId(), 15.0);
        maxSatietyMap.put(Snake.getId(), 3.0);
        maxSatietyMap.put(Wolf.getId(), 8.0);

        actualSatietyMap.put(Bear.getId(), maxSatietyMap.get(Bear.getId()));
        actualSatietyMap.put(Boar.getId(), maxSatietyMap.get(Boar.getId()));
        actualSatietyMap.put(Buffalo.getId(), maxSatietyMap.get(Buffalo.getId()));
        actualSatietyMap.put(Caterpillar.getId(), maxSatietyMap.get(Caterpillar.getId()));
        actualSatietyMap.put(Deer.getId(), maxSatietyMap.get(Deer.getId()));
        actualSatietyMap.put(Duck.getId(), maxSatietyMap.get(Duck.getId()));
        actualSatietyMap.put(Eagle.getId(), maxSatietyMap.get(Eagle.getId()));
        actualSatietyMap.put(Fox.getId(), maxSatietyMap.get(Fox.getId()));
        actualSatietyMap.put(Goat.getId(), maxSatietyMap.get(Goat.getId()));
        actualSatietyMap.put(Horse.getId(), maxSatietyMap.get(Horse.getId()));
        actualSatietyMap.put(Mouse.getId(), maxSatietyMap.get(Mouse.getId()));
        actualSatietyMap.put(Rabbit.getId(), maxSatietyMap.get(Rabbit.getId()));
        actualSatietyMap.put(Sheep.getId(), maxSatietyMap.get(Sheep.getId()));
        actualSatietyMap.put(Snake.getId(), maxSatietyMap.get(Snake.getId()));
        actualSatietyMap.put(Wolf.getId(), maxSatietyMap.get(Wolf.getId()));


        foodPoolMap.put(Bear.getId(), Map.of(Snake.getId(), 80
                , Horse.getId(), 40
                , Deer.getId(), 80
                , Rabbit.getId(), 80
                , Mouse.getId(), 90
                , Goat.getId(), 70
                , Sheep.getId(), 70
                , Boar.getId(), 50
                , Buffalo.getId(), 20
                , Duck.getId(), 10));
        foodPoolMap.put(Boar.getId(), Map.of(Plant.getId(), 100
                , Mouse.getId(), 90
                , Caterpillar.getId(), 90));
        foodPoolMap.put(Buffalo.getId(), Map.of(Plant.getId(), 100));
        foodPoolMap.put(Caterpillar.getId(), Map.of(Plant.getId(), 100));
        foodPoolMap.put(Deer.getId(), Map.of(Plant.getId(), 100));
        foodPoolMap.put(Duck.getId(), Map.of(Plant.getId(), 100
                , Caterpillar.getId(), 90));
        foodPoolMap.put(Eagle.getId(), Map.of(Fox.getId(), 10
                , Rabbit.getId(), 90
                , Mouse.getId(), 90
                , Duck.getId(), 80));
        foodPoolMap.put(Fox.getId(), Map.of(Rabbit.getId(), 70
                , Mouse.getId(), 90
                , Duck.getId(), 60
                , Caterpillar.getId(), 40));
        foodPoolMap.put(Goat.getId(), Map.of(Plant.getId(), 100));
        foodPoolMap.put(Horse.getId(), Map.of(Plant.getId(), 100));
        foodPoolMap.put(Mouse.getId(), Map.of(Plant.getId(), 100
                , Mouse.getId(), 90
                , Caterpillar.getId(), 90));
        foodPoolMap.put(Rabbit.getId(), Map.of(Plant.getId(), 100));
        foodPoolMap.put(Sheep.getId(), Map.of(Plant.getId(), 100));
        foodPoolMap.put(Snake.getId(), Map.of(Fox.getId(), 15
                , Rabbit.getId(), 20
                , Mouse.getId(), 40
                , Duck.getId(), 40));
        foodPoolMap.put(Wolf.getId(), Map.of(Horse.getId(), 10
                , Deer.getId(), 15
                , Rabbit.getId(), 60
                , Mouse.getId(), 80
                , Goat.getId(), 60
                , Sheep.getId(), 70
                , Boar.getId(), 15
                , Buffalo.getId(), 10
                , Duck.getId(), 40));

        picturesMap.put(Bear.getId(), "\uD83D\uDC3B"); // 🐻
        picturesMap.put(Boar.getId(), "\uD83D\uDC17"); // 🐗
        picturesMap.put(Buffalo.getId(), "\uD83D\uDC03"); // 🐃
        picturesMap.put(Caterpillar.getId(), "\uD83D\uDC1B"); // 🐛
        picturesMap.put(Deer.getId(), "\uD83E\uDD8C"); // 🦌
        picturesMap.put(Duck.getId(), "\uD83E\uDD86"); // 🦆
        picturesMap.put(Eagle.getId(), "\uD83E\uDD85"); // 🦅
        picturesMap.put(Fox.getId(), "\uD83E\uDD8A"); // 🦊
        picturesMap.put(Goat.getId(), "\uD83D\uDC10"); // 🐐
        picturesMap.put(Horse.getId(), "\uD83D\uDC0E"); // 🐎
        picturesMap.put(Mouse.getId(), "\uD83D\uDC01"); // 🐁
        picturesMap.put(Rabbit.getId(), "\uD83D\uDC07"); // 🐇
        picturesMap.put(Sheep.getId(), "\uD83D\uDC11"); // 🐑
        picturesMap.put(Snake.getId(), "\uD83D\uDC0D"); // 🐍
        picturesMap.put(Wolf.getId(), "\uD83D\uDC3A"); // 🐺
        picturesMap.put(Plant.getId(), "\uD83C\uDF3F"); // 🌿

        oneCellCapacityMap.put(Bear.class, 5);
        oneCellCapacityMap.put(Boar.class, 50);
        oneCellCapacityMap.put(Buffalo.class, 10);
        oneCellCapacityMap.put(Caterpillar.class, 1000);
        oneCellCapacityMap.put(Deer.class, 20);
        oneCellCapacityMap.put(Duck.class, 200);
        oneCellCapacityMap.put(Eagle.class, 20);
        oneCellCapacityMap.put(Fox.class, 30);
        oneCellCapacityMap.put(Goat.class, 140);
        oneCellCapacityMap.put(Horse.class, 20);
        oneCellCapacityMap.put(Mouse.class, 500);
        oneCellCapacityMap.put(Rabbit.class, 150);
        oneCellCapacityMap.put(Sheep.class, 140);
        oneCellCapacityMap.put(Snake.class, 30);
        oneCellCapacityMap.put(Wolf.class, 30);
    }

    public static double getWeight(int id) {
        return weightMap.get(id);
    }

    public static int getSpeed(int id) {
        return speedMap.get(id);
    }

    public static double getMaxSatiety(int id) {
        return maxSatietyMap.get(id);
    }

    public static double getActualSatiety(int id) {
        return actualSatietyMap.get(id);
    }

//    public static List<Integer> getChancesToEat(Class<? extends Animal> clazz) {
//        List<Integer> chancesToEat = new ArrayList<>();
//
//        for (Map.Entry<Class<? extends Animal>, Map<Class<?>, Integer>> entry : foodPoolMap.entrySet()) {
//            chancesToEat.add(entry.getValue().g)
//        }
//        return chancesToEat;
//    }

    public static Map<Integer, Integer> getFoodPool(int id) {
        return foodPoolMap.get(id);
    }

    public static String getPicture(int id) {
        return picturesMap.get(id);
    }

    public static void setActualSatietyMap(int id, double satiety) {
        actualSatietyMap.put(id, satiety);
    }

    // CapacityMap methods
    public static Map<Class<? extends Animal>, Integer> getOneCellCapacityMap() {
        return oneCellCapacityMap;
    }

    public static Integer getSpecificCapacity(Animals animal) {
        return oneCellCapacityMap.get(animal);
    }

    public static int getOneCellCapacity() {
        int generalCapacity = 0;

        for (Map.Entry<Class<? extends Animal>, Integer> entry : oneCellCapacityMap.entrySet()) {
            generalCapacity += entry.getValue();
        }
        return generalCapacity;
    }


    private Game() {
        throw new IllegalStateException("Utility class");
    }
}
