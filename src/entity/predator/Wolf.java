package entity.predator;

import lombok.Getter;

public class Wolf extends Predator {

    @Getter
    private static final int ID = 14;

    public Wolf () {
        super(ID);
    }

}
