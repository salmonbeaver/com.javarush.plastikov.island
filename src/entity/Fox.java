package entity;

import data.Animals;
import interfaces.Eatable;

public class Fox extends Predator implements Eatable {

    private static final int ID = 8;

    public Fox () {
        super(ID);
    }

    public static int getId() {
        return ID;
    }

}
