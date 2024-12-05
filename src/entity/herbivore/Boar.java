package entity.herbivore;

import lombok.Getter;

public class Boar extends Herbivore{

    @Getter
    private static final int ID = 1;

    public Boar () {
        super(ID);
    }

}
