package entity.herbivore;

import lombok.Getter;
import java.util.Map;


public class Boar extends Herbivore{

    @Getter
    private static final int ID = 1;
    private String picture;
    private double weight;
    private int speed;
    private double maxSatiety;
    private double actualSatiety;
    private Map<Integer, Integer> foodPool;
    private int cellNumber;

    public Boar () {
        super(ID);
    }

}
