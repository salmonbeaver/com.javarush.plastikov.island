
import data.Game;
import entity.*;
import interfaces.Eatable;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Game.init();

        Cell cell = new Cell();

        cell.populate();

        int i = 0;

        for (Map.Entry<Animal, Double> entry : cell.populationMap.entrySet()) {
//            System.out.println(entry.getKey().getClass().getSimpleName() + " - " + entry.getValue());
            i++;
        }

        System.out.println("-".repeat(100));

        for (Map.Entry<Animal, Integer> entry : cell.animalCountMap.entrySet()) {
            System.out.println(entry.getValue() + " " + entry.getKey().getClass().getSimpleName() + "s");
        }

        System.out.println("-".repeat(100));
        System.out.println(i);

    }

}
