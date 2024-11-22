package entity;

import data.Animals;

public abstract class Herbivore extends Animal{

    protected Herbivore(Animals name, String picture) {
        super(name, picture);
    }

    @Override
    public void eat(Animals animal){
        System.out.println("Затычка для хербиворов");
    }
}
