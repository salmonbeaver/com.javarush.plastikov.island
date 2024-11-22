package entity;

import data.Animals;

public class Wolf extends Predator {

    public static final String PICTURE = "\uD83D\uDC3A"; // 🐺

    public Wolf () {
        super(Animals.WOLF, PICTURE);
    }
}
