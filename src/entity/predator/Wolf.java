package entity.predator;

import lombok.Getter;
import java.util.Map;

@Getter
public class Wolf extends Predator {

    @Getter
    private static final int ID = 14;
    private String picture;
    private double weight;
    private int speed;
    private double maxSatiety;
    private double actualSatiety;
    private Map<Integer, Integer> foodPool;
    private int cellNumber;

    public Wolf () {
        super(ID);
    }

}
