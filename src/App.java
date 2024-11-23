
import data.Game;
import entity.*;
import interfaces.Eatable;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Game.init();

        Animal bear = new Bear();
        System.out.println(bear.getActualSatiety());
        bear.setActualSatiety(10.0);
        System.out.println(bear.getActualSatiety());
        System.out.println("-".repeat(100));
        System.out.println(Bear.class.getSimpleName());
        System.out.println("-".repeat(100));


        HashMap<Animal, Integer> anim = new HashMap<>(5);
        for (int i = 0; i < 5; i++) {
            anim.put(new Bear(), i);
        }


        for (Map.Entry<Animal, Integer> animalIntegerEntry : anim.entrySet()) {
            System.out.println(animalIntegerEntry.getKey().getName() + " - " + animalIntegerEntry.getValue());
        }

        System.out.println("-".repeat(100));

//        Cell cell = new Cell();
//
//        for (Map.Entry<Animal, Double> entry : cell.populationMap.entrySet()) {
//            System.out.println(entry.getKey() + " - " + entry.getValue());
//        }
//
//        cell.populate();
//
//        for (Map.Entry<Animal, Double> entry : cell.populationMap.entrySet()) {
//            System.out.println(entry.getKey().getName() + " - " + entry.getValue());
//        }

        if (Bear.class instanceof Eatable)



    }

}
