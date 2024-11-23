package entity;

import data.Animals;
import interfaces.Eatable;

public class Horse extends Herbivore implements Eatable {

    private static final int ID = 10;

    public Horse () {
        super(Horse.class);
    }
}
