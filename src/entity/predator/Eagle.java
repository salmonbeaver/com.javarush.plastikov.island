package entity.predator;

import lombok.Getter;

public class Eagle extends Predator {

    @Getter
    private static final int ID = 6;

    public Eagle () {
        super(ID);
    }

}
