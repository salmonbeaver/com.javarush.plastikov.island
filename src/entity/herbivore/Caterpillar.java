package entity.herbivore;

import lombok.Getter;

public class Caterpillar extends Herbivore{

    @Getter
    private static final int ID = 3;

    public Caterpillar () {
        super(ID);
    }

}
