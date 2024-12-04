
import entity.herbivore.Deer;
import entity.predator.Bear;
import settings.Data;
import entity.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Data.init();
        Island.init();


        for (int i = 0; i < Island.getSIZE(); i++) {
            Cell cell = Island.getCell(0);
            Thread thread = new Thread(cell);
            ExecutorService executorService = Executors.newFixedThreadPool(Data.CORES);
            executorService.execute(thread);
        }


//        System.out.println(cell.getPopulationList().size());
//
//    Animal first = cell.getPopulationList().getFirst();
//    first.move();


//        for (Animal animal : cell.getPopulationList()) {
//            System.out.println("-".repeat(50));
//            animal.reproduce();
//            animal.eat();
//        }



//        Iterator iterator = cell.populationList.iterator();
//
//        while (iterator.hasNext()) {
//            Animal animal = (Animal) iterator.next();
//
//            if (animal.eat()) {
//                iterator.remove();
//            }
//        }



//        System.out.println(cell.getPopulationList().size());




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
