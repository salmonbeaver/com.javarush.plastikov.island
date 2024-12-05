package entity.herbivore;

import lombok.Getter;

public class Duck extends Herbivore{

    @Getter
    private static final int ID = 5;

    public Duck () {
        super(ID);
    }

}
