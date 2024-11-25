package entity;

import data.*;

import java.util.*;

public abstract class Animal {

    private static final Random random = new Random();

    private static String picture;
    private static double weight;
    private static int speed;
    public static double maxSatiety;
    private double actualSatiety;
    public static Map<Integer, Integer> foodPool;

    protected Animal(Integer id) {
        picture = Game.getPicture(id);
        weight = Game.getWeight(id);
        speed = Game.getSpeed(id);
        maxSatiety = Game.getMaxSatiety(id);
        actualSatiety = Game.getActualSatiety(id);
        foodPool = Game.getFoodPool(id);
    }

    public int eat() {

        if (actualSatiety >= maxSatiety * 0.5) {
            return 0;
        }

        List<Integer> foodIDList = new ArrayList<>(foodPool.keySet()); // упорядоченные значения id еды
        int numberThatWillBeEaten = random.nextInt(foodIDList.size()); // случайный номер еды из пула доступной еды
        int foodID = foodIDList.get(numberThatWillBeEaten); // выбранный слуайный ID еды
        int chance = foodPool.get(foodID); // шанс соответсвующий этому ID
        boolean isEaten = random.nextInt(100) < chance; // проверяем, можем ли съесть еду

        if(isEaten) {
            return foodID;
        }
        return 0;
    }

    public void move(String speed) {
    }

    public void reproduce(Animal animal) {
    }

    public void die() {
    }

    public void chooseDirection() {
    }

    public Animal getAnimal(int id) {
        return null;
    }

    public static String getPicture() {
        return picture;
    }

    public double getWeight() {
        return weight;
    }

    public int getSpeed() {
        return speed;
    }

    public double getMaxSatiety() {
        return maxSatiety;
    }

    public double getActualSatiety() {
        return actualSatiety;
    }

    public void setActualSatiety(Double actualSatiety) {
        this.actualSatiety = actualSatiety;
    }

    public Map<Integer, Integer> getFoodPool() {
        return foodPool;
    }
}
