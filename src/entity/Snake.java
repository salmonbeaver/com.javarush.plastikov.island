package entity;

import data.Animals;
import interfaces.Eatable;

public class Snake extends Predator implements Eatable {

    public static final String PICTURE = "\uD83D\uDC0D"; // 🐍

    public Snake () {
        super(Animals.SNAKE, PICTURE);
    }
}
