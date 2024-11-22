package entity;

import data.Animals;
import interfaces.Eatable;

public class Buffalo extends Herbivore implements Eatable {

    public static final String PICTURE = "\uD83D\uDC03"; // 🐃

    public Buffalo () {
        super(Animals.BUFFALO, PICTURE);
    }
}
