package entity.predator;

import lombok.Getter;

public class Snake extends Predator{

    @Getter
    private static final int ID = 13;

    public Snake () {
        super(ID);
    }

}
