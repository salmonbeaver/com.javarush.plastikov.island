package entity.herbivore;

import lombok.Getter;

public class Buffalo extends Herbivore{

    @Getter
    private static final int ID = 2;

    public Buffalo () {
        super(ID);
    }

}
