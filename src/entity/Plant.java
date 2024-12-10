package entity;

import lombok.Getter;
import lombok.Setter;

@Setter
public class Plant {

    @Getter private static final int ID = 15;
    @Getter private static int capacity = 200;
    @Getter private static double weight = 1;

    @Getter private static int growSpeed = capacity / 2;

}
