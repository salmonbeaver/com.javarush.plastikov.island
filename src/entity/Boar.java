package entity;

import data.Animals;
import interfaces.Eatable;

public class Boar extends Herbivore implements Eatable {

    public static final String PICTURE = "\uD83D\uDC17"; // 🐗

    public Boar () {
        super(Animals.BEAR, PICTURE);
    }
}
