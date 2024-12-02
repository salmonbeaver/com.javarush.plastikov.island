package entity.herbivore;

import lombok.Getter;
import java.util.Map;

@Getter
public class Deer extends Herbivore{

    @Getter
    private static final int ID = 4;
    private String picture;
    private double weight;
    private int speed;
    private double maxSatiety;
    private double actualSatiety;
    private Map<Integer, Integer> foodPool;
    private int cellNumber;

    public Deer () {
        super(ID);
    }

}