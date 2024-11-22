package entity;

import data.Animals;
import interfaces.Eatable;

public class Duck extends Herbivore implements Eatable {

    public static final String PICTURE = "\uD83E\uDD86"; // 🦆

    public Duck () {
        super(Animals.DUCK, PICTURE);
    }
}
