package entity;

import data.Animals;
import interfaces.Eatable;

public class Sheep extends Herbivore implements Eatable {

    public static final String PICTURE = "\uD83D\uDC11"; // 🐑

    public Sheep () {
        super(Animals.SHEEP, PICTURE);
    }
}
