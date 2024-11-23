package entity;

import data.Animals;

public abstract class Predator extends Animal {

    protected Predator(Class<? extends Animal> name) {
        super(name);
    }

    @Override
    public void eat(Animals animal){
        System.out.println("Затычка для предаторов");
    }
}
