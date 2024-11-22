package entity;

import data.Animals;

public class Eagle extends Predator {

    public static final String PICTURE = "\uD83E\uDD85"; // 🦅

    public Eagle () {
        super(Animals.EAGLE, PICTURE);
    }
}
