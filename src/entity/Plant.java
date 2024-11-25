package entity;

import interfaces.Eatable;

public class Plant implements Eatable {

    private double weight = 1;
    private static final int ID = 16;
    private static int capacity = 200;

    public Plant() {}

    public double getWeight() {
        return weight;
    }

    public static int getId() {
        return ID;
    }

    public static int getCapacity() {
        return capacity;
    }
}
