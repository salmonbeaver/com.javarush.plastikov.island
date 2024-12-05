package entity.herbivore;

import lombok.Getter;

public class Mouse extends Herbivore{

    @Getter
    private static final int ID = 10;

    public Mouse () {
        super(ID);
    }

}
