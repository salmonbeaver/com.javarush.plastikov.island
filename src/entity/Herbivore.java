package entity;

import data.Animals;

public abstract class Herbivore extends Animal{

    protected Herbivore(Class<? extends Animal> name) {
        super(name);
    }

    @Override
    public void eat(Animals animal){
        System.out.println("Затычка для хербиворов");
    }
}
