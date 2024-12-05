package entity.predator;

import lombok.Getter;

public class Bear extends Predator {

    @Getter
    private static final int ID = 0;

    public Bear () {
        super(ID);
    }

}
