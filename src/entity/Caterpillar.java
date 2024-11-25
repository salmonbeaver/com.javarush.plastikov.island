package entity;

import data.Animals;
import interfaces.Eatable;

public class Caterpillar extends Herbivore implements Eatable {

    private static final int ID = 4;

    public Caterpillar () {
        super(ID);
    }

    public static int getId() {
        return ID;
    }

}
