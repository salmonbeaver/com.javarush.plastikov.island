package entity;

import interfaces.Eatable;

public class Plant implements Eatable {

    public static final String PICTURE = "\uD83C\uDF3F"; // 🌿
    double weight = 1;

    public double getWeight() {
        return weight;
    }
}
