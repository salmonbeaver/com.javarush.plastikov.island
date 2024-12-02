package entity;

import lombok.Getter;
import lombok.Setter;
import settings.*;

import java.lang.reflect.Field;
import java.util.*;
@Getter
@Setter
public abstract class Animal {

    private static final Random random = new Random();
    @Getter
    private int ID;
    private String picture;
    private double weight;
    private int speed;
    private double maxSatiety;
    private double actualSatiety;
    private Map<Integer, Integer> foodPool;
    private int cellNumber;

    protected Animal(int id) {
        ID = id;
        picture = Data.getPicture(id);
        weight = Data.getWeight(id);
        speed = Data.getSpeed(id);
        maxSatiety = Data.getMaxSatiety(id);
        actualSatiety = maxSatiety * 0.1;
        foodPool = Data.getFoodPool(id);
    }

    // не работает
    public boolean eat() {

        if (actualSatiety >= maxSatiety * 0.5) {
            return false;
        }

        List<Integer> foodIDList = new ArrayList<>(getActualFoodPool().keySet()); // упорядоченные значения id еды
        int numberThatWillBeEaten = random.nextInt(foodIDList.size()); // случайный номер еды из пула доступной еды
        int foodID = foodIDList.get(numberThatWillBeEaten); // выбранный слуайный ID еды
        int chance = getActualFoodPool().get(foodID); // шанс соответсвующий этому ID
        boolean canEat = random.nextInt(100) < chance; // проверяем, можем ли съесть еду

        if (chance == 100) {
            Island.getCell(cellNumber).plantList.removeLast();
            System.out.println(Data.getPicture(ID) + " eat gross" );
            return true;
        }

        if(canEat) {
            List<Animal> populationList = Island.getCell(cellNumber).populationList;

            for (Animal animal : populationList) {
                if (getID() == foodID) {
                    populationList.remove(animal);
                    if (actualSatiety + animal.getWeight() <= maxSatiety) { // если вес скушанного больше веса едока, то сытость = макс
                        actualSatiety += animal.getWeight();
                    } else {
                        actualSatiety = maxSatiety;
                    }
                    System.out.println(Data.getPicture(ID) + " eat " + Data.getPicture(foodID));

                    return true;
                }
            }

        }

        return false;
    }

    public void move() {

    }

//    public void reproduce() {
//
//        Cell cell = Island.getCell(cellNumber);
//        List<Animal> newPopulation
//        for (Animal animal : cell.populationList) {
//            int check = getIDByReflection(animal);
//
//            if (check == getID()) {
//
//            }
//        }
//    }

    public void die() {
    }

    public void chooseDirection() {
    }

    private Map<Integer, Integer> getActualFoodPool() {
        Map<Integer, Integer> actualFoodPool = new HashMap<>();
        List<Animal> populationList = Island.getCell(cellNumber).getAnimals();

        // Делаем выборку доступной еды из находящихся на клетке живности
        for (Map.Entry<Integer, Integer> entry : foodPool.entrySet()) {

            for (Animal animal : populationList) {
                int check = getIDByReflection(animal);

                if (check == entry.getKey()) {
                    actualFoodPool.put(entry.getKey(), entry.getValue());
                }

                // Все животные из пула еды в клетке
                if(foodPool.size() == actualFoodPool.size()) {
                    return actualFoodPool;
                }
            }
        }

        System.out.println(actualFoodPool.size());

        return actualFoodPool;
    }

    private int getIDByReflection(Animal animal) {
        int check = -1;

        try {
            Field fieldID = animal.getClass().getDeclaredField("ID");
            fieldID.setAccessible(true);
            check = (int) fieldID.get(ID);
        } catch (Exception e) {
            System.out.println("Вы что-нибудь видели? И я нет -_-");
        }

        return check;
    }
}
