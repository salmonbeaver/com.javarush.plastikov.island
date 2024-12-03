
import entity.herbivore.Deer;
import entity.predator.Bear;
import settings.Data;
import entity.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

public class App {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Data.init();
        Island.init();
        Cell cell = Island.getCell(0);
        Animal test = cell.populationList.getFirst();

        System.out.println(cell.populationList.size());
//        System.out.println(test.eat());
        System.out.println(test.reproduce());

//        Iterator iterator = cell.populationList.iterator();
//
//        while (iterator.hasNext()) {
//            Animal animal = (Animal) iterator.next();
//
//            if (animal.eat()) {
//                iterator.remove();
//            }
//        }



        System.out.println(cell.populationList.size());




//
//        Cell cell = new Cell();
//
//        cell.populate();
//
//        int i = 0;
//
//        for (Animal _ : cell.populationList) {
//            i++;
//        }
//
//        System.out.println("-".repeat(50));
//
//        for (Map.Entry<Animal, Integer> entry : cell.animalCountMap.entrySet()) {
//            System.out.println(entry.getValue() + " " + entry.getKey().getClass().getSimpleName() + "s");
//        }
//
//        System.out.println("-".repeat(50));
//        System.out.println(i + " out of " + Data.getOneCellMaxCapacity());
//
//        System.out.println("-".repeat(50));
////        cell.live();
//        Animal animal = new Bear();
//        System.out.println(animal.getId());
//        Bear.getId();

    }

}
