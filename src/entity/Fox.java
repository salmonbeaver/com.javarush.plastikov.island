package entity;

import data.Animals;
import interfaces.Eatable;

public class Fox extends Predator implements Eatable {

    public static final String PICTURE = "\uD83E\uDD8A"; // 🦊

    public Fox () {
        super(Animals.FOX, PICTURE);
    }
}
