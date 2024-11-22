package entity;

import data.Animals;
import interfaces.Eatable;

public class Rabbit extends Herbivore implements Eatable {

    public static final String PICTURE = "\uD83D\uDC07"; // 🐇

    public Rabbit () {
        super(Animals.RABBIT, PICTURE);
    }
}
