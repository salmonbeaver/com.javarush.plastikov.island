package entity;

import data.Animals;
import interfaces.Eatable;

public class Horse extends Herbivore implements Eatable {

    public static final String PICTURE = "\uD83D\uDC0E"; // 🐎

    public Horse () {
        super(Animals.HORSE, PICTURE);
    }
}
