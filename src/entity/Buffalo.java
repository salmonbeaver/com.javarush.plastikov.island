package entity;

import data.Animals;
import interfaces.Eatable;

public class Buffalo extends Herbivore implements Eatable {

    private static final int ID = 3;

    public Buffalo () {
        super(Buffalo.class);
    }
}
