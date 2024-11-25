package entity;

import data.Animals;
import interfaces.Eatable;

public class Mouse extends Herbivore implements Eatable {

    private static final int ID = 11;

    public Mouse () {
        super(ID);
    }

    public static int getId() {
        return ID;
    }

}
