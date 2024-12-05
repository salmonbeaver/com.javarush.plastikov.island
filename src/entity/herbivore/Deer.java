package entity.herbivore;

import lombok.Getter;

public class Deer extends Herbivore{

    @Getter
    private static final int ID = 4;

    public Deer () {
        super(ID);
    }

}
