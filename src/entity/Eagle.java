package entity;

import data.Animals;

public class Eagle extends Predator {

    private static final int ID = 7;

    public Eagle () {
        super(ID);
    }

    public static int getId() {
        return ID;
    }

}
