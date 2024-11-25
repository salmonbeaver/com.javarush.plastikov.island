package entity;

import data.Animals;

public class Bear extends Predator {

    private static final int ID = 1;

    public Bear () {
        super(ID);
    }

    public static int getId() {
        return ID;
    }

}
