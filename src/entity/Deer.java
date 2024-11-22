package entity;

import data.Animals;
import interfaces.Eatable;

public class Deer extends Herbivore implements Eatable {

    public static final String PICTURE = "\uD83E\uDD8C"; // 🦌

    public Deer () {
        super(Animals.DEER, PICTURE);
    }
}
