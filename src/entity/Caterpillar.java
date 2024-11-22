package entity;

import data.Animals;
import interfaces.Eatable;

public class Caterpillar extends Herbivore implements Eatable {

    public static final String PICTURE = "\uD83D\uDC1B"; // 🐛

    public Caterpillar () {
        super(Animals.CATERPILLAR, PICTURE);
    }
}
