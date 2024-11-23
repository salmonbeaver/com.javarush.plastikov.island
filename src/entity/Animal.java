package entity;

import data.*;
import interfaces.Eatable;

import java.util.*;

public abstract class Animal {

    private static int ID;
    private String name;
    private String picture;
    private double weight;
    private int speed;
    private double maxSatiety;
    private double actualSatiety;
    private Map<Integer, Integer> foodPool;

    protected Animal(int id) {
        ID = id;
        picture = Game.getPicture(id);
        weight = Game.getWeight(id);
        speed = Game.getSpeed(id);
        maxSatiety = Game.getMaxSatiety(id);
        actualSatiety = Game.getActualSatiety(id);
        foodPool = Game.getFoodPool(id);
    }

    public int eat() {
        List<Integer> values = new ArrayList<>(foodPool.values());
        Random random = new Random();
        int numberThatWillBeEaten = random.nextInt(values.size());
        boolean isEaten = random.nextInt() < values.get(numberThatWillBeEaten);

        if(isEaten) {
            return 0; // тут закончил
        }
    }

    public void move(String speed) {
    }

    public void reproduce(Animal animal) {
    }

    public void die() {
    }

    public void chooseDirection() {
    }

    public static int getId() {
        return ID;
    }

    public Animal getAnimal(int id) {
        return null;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
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
}
