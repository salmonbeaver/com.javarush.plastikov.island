package entity;

import data.Animals;
import interfaces.Eatable;

public class Duck extends Herbivore implements Eatable {

    private static final int ID = 6;

    public Duck () {
        super(ID);
    }

    public static int getId() {
        return ID;
    }

}
