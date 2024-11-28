package entity;

import settings.*;

import java.util.*;

public abstract class Animal {

    private static final Random random = new Random();

    private static int ID;
    private static String picture;
    private static double weight;
    private static int speed;
    private static double maxSatiety;
    private double actualSatiety;
    private static Map<Integer, Integer> foodPool;
    private int cellNumber = -1;

    protected Animal(int id) {
        ID = id;
        picture = Data.getPicture(id);
        weight = Data.getWeight(id);
        speed = Data.getSpeed(id);
        maxSatiety = Data.getMaxSatiety(id);
        actualSatiety = maxSatiety;
        foodPool = Data.getFoodPool(id);
    }

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
                if (animal.getId() == foodID) {
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

    private Map<Integer, Integer> getActualFoodPool() {
        Map<Integer, Integer> actualFoodPool = new HashMap<>();

        // Делаем выборку доступной еды из находящихся на клетке живности
        for (Map.Entry<Integer, Integer> entry : foodPool.entrySet()) {
            List<Animal> populationList = Island.getCell(cellNumber).populationList;

            for (Animal animal : populationList) {
                if (animal.getId() == entry.getKey()) {
                    actualFoodPool.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return actualFoodPool;
    }

    public void move() {
    }

    public void reproduce() {
    }

    public void die() {
    }

    public void chooseDirection() {
    }

    public Animal getAnimal(int id) {
        return null;
    }

    public static String getPicture() {
        return picture;
    }

    public double getWeight() {
        return weight;
    }

    public int getSpeed() {
        return speed;
    }

    public double getMaxSatiety() {
        return maxSatiety;
    }

    public double getActualSatiety() {
        return actualSatiety;
    }

    public void setActualSatiety(Double actualSatiety) {
        this.actualSatiety = actualSatiety;
    }

    public void setCellNumber(int i) {
        cellNumber = i;
    }

    public Map<Integer, Integer> getFoodPool() {
        return foodPool;
    }

    public static int getId() {
        return ID;
    }
}
