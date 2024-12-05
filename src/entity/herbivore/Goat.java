package entity.herbivore;

import lombok.Getter;

public class Goat extends Herbivore{

    @Getter
    private static final int ID = 8;

    public Goat () {
        super(ID);
    }

}
