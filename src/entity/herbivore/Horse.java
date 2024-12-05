package entity.herbivore;

import lombok.Getter;

public class Horse extends Herbivore{

    @Getter
    private static final int ID = 9;

    public Horse () {
        super(ID);
    }

}
