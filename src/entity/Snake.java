package entity;

import data.Animals;
import interfaces.Eatable;

public class Snake extends Predator implements Eatable {

    private static final int ID = 14;

    public Snake () {
        super(ID);
    }

    public static int getId() {
        return ID;
    }

}
