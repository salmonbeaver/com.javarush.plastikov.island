package entity;

import data.Animals;
import interfaces.Eatable;

public class Sheep extends Herbivore implements Eatable {

    private static final int ID = 13;

    public Sheep () {
        super(ID);
    }

    public static int getId() {
        return ID;
    }

}
