package entity.predator;

import lombok.Getter;
import java.util.Map;


public class Snake extends Predator{

    @Getter
    private static final int ID = 13;
    private String picture;
    private double weight;
    private int speed;
    private double maxSatiety;
    private double actualSatiety;
    private Map<Integer, Integer> foodPool;
    private int cellNumber;

    public Snake () {
        super(ID);
    }

}
