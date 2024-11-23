package entity;

import data.Animals;
import interfaces.Eatable;

public class Rabbit extends Herbivore implements Eatable {

    private static final int ID = 12;

    public Rabbit () {
        super(Rabbit.class);
    }
}
