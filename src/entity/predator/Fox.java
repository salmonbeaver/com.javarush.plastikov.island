package entity.predator;

import lombok.Getter;

public class Fox extends Predator{

    @Getter
    private static final int ID = 7;

    public Fox () {
        super(ID);
    }

}
