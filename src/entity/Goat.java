package entity;

import data.Animals;
import interfaces.Eatable;

public class Goat extends Herbivore implements Eatable {

    private static final int ID = 9;

    public Goat () {
        super(Goat.class);
    }
}
