package entity;

import data.Animals;
import interfaces.Eatable;

public class Boar extends Herbivore implements Eatable {

    private static final int ID = 2;

    public Boar () {
        super(Boar.class);
    }
}
