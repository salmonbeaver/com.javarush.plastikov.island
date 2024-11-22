package entity;

import data.Animals;
import interfaces.Eatable;

public class Goat extends Herbivore implements Eatable {

    public static final String PICTURE = "\uD83D\uDC10"; // 🐐

    public Goat () {
        super(Animals.GOAT, PICTURE);
    }
}
