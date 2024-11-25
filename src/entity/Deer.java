package entity;

import data.Animals;
import interfaces.Eatable;

public class Deer extends Herbivore implements Eatable {

    private static final int ID = 5;

    public Deer () {
        super(ID);
    }

    public static int getId() {
        return ID;
    }

}
