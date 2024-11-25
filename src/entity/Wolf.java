package entity;

import data.Animals;

public class Wolf extends Predator {

    private static final int ID = 15;

    public Wolf () {
        super(ID);
    }

    public static int getId() {
        return ID;
    }

}
