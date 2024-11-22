package entity;

import data.Animals;

public class Bear extends Predator {

    public static final String PICTURE = "\uD83D\uDC3B"; // 🐻

    public Bear () {
        super(Animals.BEAR, PICTURE);
    }
}
