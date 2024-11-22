package entity;

import data.Animals;

public abstract class Animal {

    private static Animals name;
    private static String picture;
    private static double weight;
    private static int speed;
    private static double maxSatiety;
    private static double actualSatiety;

    protected Animal (Animals name, String picture) {
        weight = Settings.getWeight(name);
        speed = Settings.getSpeed(name);
        maxSatiety = Settings.getMaxSatiety(name);
        actualSatiety = Settings.getActualSatiety(name);
        this.picture = picture;
    }

    public void eat(Animals animals) {

    }

    public void move(String speed) {
    }

    public void reproduce(Animal animal) {
    }

    public void die() {
    }

    public void chooseDirection() {
    }

    public static String getPicture() {
        return picture;
    }

    public static double getWeight() {
        return weight;
    }

    public static int getSpeed() {
        return speed;
    }

    public static double getMaxSatiety() {
        return maxSatiety;
    }

    public double getActualSatiety() {
        return actualSatiety;
    }
}
