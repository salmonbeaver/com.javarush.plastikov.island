package entity;

import data.Animals;
import interfaces.Eatable;

public class Mouse extends Herbivore implements Eatable {

    public static final String PICTURE = "\uD83D\uDC01"; // 🐁

    public Mouse () {
        super(Animals.MOUSE, PICTURE);
    }
}
