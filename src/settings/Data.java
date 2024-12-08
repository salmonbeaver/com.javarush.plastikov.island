package settings;

import entity.*;
import entity.herbivore.*;
import entity.predator.*;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Data {

    public static final int OBSERVE_DAYS = 10;
    public static final int CORES = Runtime.getRuntime().availableProcessors();

    public static final Map<Integer, Class<? extends Animal>> matchID = new HashMap<>();
    protected static final Map<Integer, Double> weightMap = new HashMap<>(); // Коллекция весов
    protected static final Map<Integer, Integer> speedMap = new HashMap<>(); // Коллекция скоростей
    protected static final Map<Integer, Double> maxSatietyMap = new HashMap<>(); // Коллекция макс сытостей
    protected static Map<Integer, Double> actualSatietyMap = new HashMap<>(); // Коллекция актуальных сытостей
    protected static final Map<Integer, Map<Integer, Integer>> foodPoolMap = new HashMap<>(); // Коллекция кто кого ест
    protected static final Map<Integer, String> picturesMap = new HashMap<>(); // Коллекция эмодзи
    @Getter protected static final Map<Integer, Integer> oneCellCapacityMap = new HashMap<>(); // Коллекция вместимостей в одной клетке

    public static void init() {

        matchID.put(Bear.getID(), Bear.class);
        matchID.put(Boar.getID(), Boar.class);
        matchID.put(Buffalo.getID(), Buffalo.class);
        matchID.put(Caterpillar.getID(), Caterpillar.class);
        matchID.put(Deer.getID(), Deer.class);
        matchID.put(Duck.getID(), Duck.class);
        matchID.put(Eagle.getID(), Eagle.class);
        matchID.put(Fox.getID(), Fox.class);
        matchID.put(Goat.getID(), Goat.class);
        matchID.put(Horse.getID(), Horse.class);
        matchID.put(Mouse.getID(), Mouse.class);
        matchID.put(Rabbit.getID(), Rabbit.class);
        matchID.put(Sheep.getID(), Sheep.class);
        matchID.put(Snake.getID(), Snake.class);
        matchID.put(Wolf.getID(), Wolf.class);
//        matchID.put(Plant.getId(), Plant.class);

        weightMap.put(Bear.getID(), 500.0);
        weightMap.put(Boar.getID(), 400.0);
        weightMap.put(Buffalo.getID(), 700.0);
        weightMap.put(Caterpillar.getID(), 0.01);
        weightMap.put(Deer.getID(), 300.0);
        weightMap.put(Duck.getID(), 1.0);
        weightMap.put(Eagle.getID(), 6.0);
        weightMap.put(Fox.getID(), 8.0);
        weightMap.put(Goat.getID(), 60.0);
        weightMap.put(Horse.getID(), 400.0);
        weightMap.put(Mouse.getID(), 0.05);
        weightMap.put(Rabbit.getID(), 2.0);
        weightMap.put(Sheep.getID(), 70.0);
        weightMap.put(Snake.getID(), 15.0);
        weightMap.put(Wolf.getID(), 50.0);

        speedMap.put(Bear.getID(), 2);
        speedMap.put(Boar.getID(), 2);
        speedMap.put(Buffalo.getID(), 3);
        speedMap.put(Caterpillar.getID(), 0);
        speedMap.put(Deer.getID(), 4);
        speedMap.put(Duck.getID(), 4);
        speedMap.put(Eagle.getID(), 3);
        speedMap.put(Fox.getID(), 2);
        speedMap.put(Goat.getID(), 3);
        speedMap.put(Horse.getID(), 4);
        speedMap.put(Mouse.getID(), 1);
        speedMap.put(Rabbit.getID(), 2);
        speedMap.put(Sheep.getID(), 3);
        speedMap.put(Snake.getID(), 1);
        speedMap.put(Wolf.getID(), 3);

        maxSatietyMap.put(Bear.getID(), 80.0);
        maxSatietyMap.put(Boar.getID(), 50.0);
        maxSatietyMap.put(Buffalo.getID(), 100.0);
        maxSatietyMap.put(Caterpillar.getID(), 0.0);
        maxSatietyMap.put(Deer.getID(), 50.0);
        maxSatietyMap.put(Duck.getID(), 0.015);
        maxSatietyMap.put(Eagle.getID(), 1.0);
        maxSatietyMap.put(Fox.getID(), 2.0);
        maxSatietyMap.put(Goat.getID(), 10.0);
        maxSatietyMap.put(Horse.getID(), 60.0);
        maxSatietyMap.put(Mouse.getID(), 0.01);
        maxSatietyMap.put(Rabbit.getID(), 0.45);
        maxSatietyMap.put(Sheep.getID(), 15.0);
        maxSatietyMap.put(Snake.getID(), 3.0);
        maxSatietyMap.put(Wolf.getID(), 8.0);

        actualSatietyMap.put(Bear.getID(), maxSatietyMap.get(Bear.getID()));
        actualSatietyMap.put(Boar.getID(), maxSatietyMap.get(Boar.getID()));
        actualSatietyMap.put(Buffalo.getID(), maxSatietyMap.get(Buffalo.getID()));
        actualSatietyMap.put(Caterpillar.getID(), maxSatietyMap.get(Caterpillar.getID()));
        actualSatietyMap.put(Deer.getID(), maxSatietyMap.get(Deer.getID()));
        actualSatietyMap.put(Duck.getID(), maxSatietyMap.get(Duck.getID()));
        actualSatietyMap.put(Eagle.getID(), maxSatietyMap.get(Eagle.getID()));
        actualSatietyMap.put(Fox.getID(), maxSatietyMap.get(Fox.getID()));
        actualSatietyMap.put(Goat.getID(), maxSatietyMap.get(Goat.getID()));
        actualSatietyMap.put(Horse.getID(), maxSatietyMap.get(Horse.getID()));
        actualSatietyMap.put(Mouse.getID(), maxSatietyMap.get(Mouse.getID()));
        actualSatietyMap.put(Rabbit.getID(), maxSatietyMap.get(Rabbit.getID()));
        actualSatietyMap.put(Sheep.getID(), maxSatietyMap.get(Sheep.getID()));
        actualSatietyMap.put(Snake.getID(), maxSatietyMap.get(Snake.getID()));
        actualSatietyMap.put(Wolf.getID(), maxSatietyMap.get(Wolf.getID()));

        foodPoolMap.put(Bear.getID(), Map.of(
                Snake.getID(), 80
                , Horse.getID(), 40
                , Deer.getID(), 80
                , Rabbit.getID(), 80
                , Mouse.getID(), 90
                , Goat.getID(), 70
                , Sheep.getID(), 70
                , Boar.getID(), 50
                , Buffalo.getID(), 20
                , Duck.getID(), 10));
        foodPoolMap.put(Boar.getID(), Map.of(
                Plant.getID(), 100
                , Mouse.getID(), 90
                , Caterpillar.getID(), 90));
        foodPoolMap.put(Buffalo.getID(), Map.of(
                Plant.getID(), 100));
        foodPoolMap.put(Caterpillar.getID(), Map.of(
                Plant.getID(), 100));
        foodPoolMap.put(Deer.getID()
                , Map.of(Plant.getID(), 100));
        foodPoolMap.put(Duck.getID(), Map.of(
                Plant.getID(), 100
                , Caterpillar.getID(), 90));
        foodPoolMap.put(Eagle.getID(), Map.of(
                Fox.getID(), 10
                , Rabbit.getID(), 90
                , Mouse.getID(), 90
                , Duck.getID(), 80));
        foodPoolMap.put(Fox.getID(), Map.of(
                Rabbit.getID(), 70
                , Mouse.getID(), 90
                , Duck.getID(), 60
                , Caterpillar.getID(), 40));
        foodPoolMap.put(Goat.getID(), Map.of(
                Plant.getID(), 100));
        foodPoolMap.put(Horse.getID(), Map.of(
                Plant.getID(), 100));
        foodPoolMap.put(Mouse.getID(), Map.of(
                Plant.getID(), 100
                , Mouse.getID(), 90
                , Caterpillar.getID(), 90));
        foodPoolMap.put(Rabbit.getID(), Map.of(
                Plant.getID(), 100));
        foodPoolMap.put(Sheep.getID(), Map.of(
                Plant.getID(), 100));
        foodPoolMap.put(Snake.getID(), Map.of(
                Fox.getID(), 15
                , Rabbit.getID(), 20
                , Mouse.getID(), 40
                , Duck.getID(), 40));
        foodPoolMap.put(Wolf.getID(), Map.of(
                Horse.getID(), 10
                , Deer.getID(), 15
                , Rabbit.getID(), 60
                , Mouse.getID(), 80
                , Goat.getID(), 60
                , Sheep.getID(), 70
                , Boar.getID(), 15
                , Buffalo.getID(), 10
                , Duck.getID(), 40));

        picturesMap.put(Bear.getID(), "\uD83D\uDC3B"); // 🐻
        picturesMap.put(Boar.getID(), "\uD83D\uDC17"); // 🐗
        picturesMap.put(Buffalo.getID(), "\uD83D\uDC03"); // 🐃
        picturesMap.put(Caterpillar.getID(), "\uD83D\uDC1B"); // 🐛
        picturesMap.put(Deer.getID(), "\uD83E\uDD8C"); // 🦌
        picturesMap.put(Duck.getID(), "\uD83E\uDD86"); // 🦆
        picturesMap.put(Eagle.getID(), "\uD83E\uDD85"); // 🦅
        picturesMap.put(Fox.getID(), "\uD83E\uDD8A"); // 🦊
        picturesMap.put(Goat.getID(), "\uD83D\uDC10"); // 🐐
        picturesMap.put(Horse.getID(), "\uD83D\uDC0E"); // 🐎
        picturesMap.put(Mouse.getID(), "\uD83D\uDC01"); // 🐁
        picturesMap.put(Rabbit.getID(), "\uD83D\uDC07"); // 🐇
        picturesMap.put(Sheep.getID(), "\uD83D\uDC11"); // 🐑
        picturesMap.put(Snake.getID(), "\uD83D\uDC0D"); // 🐍
        picturesMap.put(Wolf.getID(), "\uD83D\uDC3A"); // 🐺
        picturesMap.put(Plant.getID(), "\uD83C\uDF3F"); // 🌿

//        oneCellCapacityMap.put(Bear.getID(), 3);
//        oneCellCapacityMap.put(Boar.getID(), 3);
//        oneCellCapacityMap.put(Buffalo.getID(), 3);
//        oneCellCapacityMap.put(Caterpillar.getID(), 3);
//        oneCellCapacityMap.put(Deer.getID(), 3);
//        oneCellCapacityMap.put(Duck.getID(), 3);
//        oneCellCapacityMap.put(Eagle.getID(), 3);
//        oneCellCapacityMap.put(Fox.getID(), 3);
//        oneCellCapacityMap.put(Goat.getID(), 3);
//        oneCellCapacityMap.put(Horse.getID(), 3);
//        oneCellCapacityMap.put(Mouse.getID(), 3);
//        oneCellCapacityMap.put(Rabbit.getID(), 3);
//        oneCellCapacityMap.put(Sheep.getID(), 3);
//        oneCellCapacityMap.put(Snake.getID(), 3);
//        oneCellCapacityMap.put(Wolf.getID(), 3);

        oneCellCapacityMap.put(Bear.getID(), 5);
        oneCellCapacityMap.put(Boar.getID(), 50);
        oneCellCapacityMap.put(Buffalo.getID(), 10);
        oneCellCapacityMap.put(Caterpillar.getID(), 1000);
        oneCellCapacityMap.put(Deer.getID(), 20);
        oneCellCapacityMap.put(Duck.getID(), 200);
        oneCellCapacityMap.put(Eagle.getID(), 20);
        oneCellCapacityMap.put(Fox.getID(), 30);
        oneCellCapacityMap.put(Goat.getID(), 140);
        oneCellCapacityMap.put(Horse.getID(), 20);
        oneCellCapacityMap.put(Mouse.getID(), 500);
        oneCellCapacityMap.put(Rabbit.getID(), 150);
        oneCellCapacityMap.put(Sheep.getID(), 140);
        oneCellCapacityMap.put(Snake.getID(), 30);
        oneCellCapacityMap.put(Wolf.getID(), 30);

    }

    public static double getWeightByID(int id) {
        return weightMap.get(id);
    }

    public static int getSpeedByID(int id) {
        return speedMap.get(id);
    }

    public static double getMaxSatietyByID(int id) {
        return maxSatietyMap.get(id);
    }

    public static Map<Integer, Integer> getFoodPoolByID(int id) {
        return foodPoolMap.get(id);
    }

    public static String getPictureByID(int id) {
        return picturesMap.get(id);
    }

    public static void setActualSatietyMap(int id, double satiety) {
        actualSatietyMap.put(id, satiety);
    }

    public static Integer getSpecificCapacity(int id) {
        return oneCellCapacityMap.get(id);
    }

    // Максимальная вместимость 1 клетки
    public static int getOneCellMaxCapacity() {
        int animalCapacity = 0;

        for (Map.Entry<Integer, Integer> entry : oneCellCapacityMap.entrySet()) {
            animalCapacity += entry.getValue();
        }
        return animalCapacity;
    }

    public static Class<? extends Animal> getClassByID(int id) {
        return matchID.get(id);
    }


    private Data() {
        throw new IllegalStateException("Utility class");
    }
}
