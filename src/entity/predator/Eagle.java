package entity.predator;

import lombok.Getter;
import java.util.Map;

@Getter
public class Eagle extends Predator {

    @Getter
    private static final int ID = 6;
    private String picture;
    private double weight;
    private int speed;
    private double maxSatiety;
    private double actualSatiety;
    private Map<Integer, Integer> foodPool;
    private int cellNumber;

    public Eagle () {
        super(ID);
    }

}
