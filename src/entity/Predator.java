package entity;

import data.Animals;
import interfaces.Eatable;

public abstract class Predator extends Animal {

    protected Predator(Animals name, String picture) {
        super(name, picture);
    }

    @Override
    public void eat(Animals animal){
        System.out.println("Затычка для предаторов");
    }
}
