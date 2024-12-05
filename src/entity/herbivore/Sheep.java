package entity.herbivore;

import lombok.Getter;

public class Sheep extends Herbivore{

    @Getter
    private static final int ID = 12;

    public Sheep () {
        super(ID);
    }

}
