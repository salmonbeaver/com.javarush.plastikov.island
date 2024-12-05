package entity.herbivore;

import lombok.Getter;

public class Rabbit extends Herbivore{

    @Getter
    private static final int ID = 11;

    public Rabbit () {
        super(ID);
    }

}
