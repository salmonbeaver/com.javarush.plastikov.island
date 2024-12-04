package entity.herbivore;

import lombok.Getter;
import java.util.Map;


public class Horse extends Herbivore{

    @Getter
    private static final int ID = 9;
    private String picture;
    private double weight;
    private int speed;
    private double maxSatiety;
    private double actualSatiety;
    private Map<Integer, Integer> foodPool;
    private int cellNumber;

    public Horse () {
        super(ID);
    }

}
