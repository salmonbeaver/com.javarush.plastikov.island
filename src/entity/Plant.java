package entity;

import interfaces.Eatable;

public class Plant implements Eatable {

    double weight = 1;
    private static final int ID = 16;

    public double getWeight() {
        return weight;
    }

    public static int getId() {
        return ID;
    }
}
