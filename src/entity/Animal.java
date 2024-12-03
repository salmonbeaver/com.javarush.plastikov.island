package entity;

import lombok.Setter;
import settings.*;

import java.lang.reflect.Field;
import java.util.*;

@Setter
public abstract class Animal {

    private static final Random random = new Random();

    private int ID;
    private String picture;
    private double weight;
    private int speed;
    private double maxSatiety;
    private double actualSatiety;
    private Map<Integer, Integer> foodPool;
    private int cellID;

    public Animal(int id) {
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
            Island.getCell(cellID).plantList.removeLast();
            System.out.println(Data.getPicture(ID) + " ate gross" );
            return true;
        }

        if(canEat) {
            List<Animal> populationList = Island.getCell(cellID).populationList;
            Iterator iterator = populationList.iterator();

            while (iterator.hasNext()) {
                Animal animal = (Animal) iterator.next();

                if (animal.getIDByReflection(animal) == foodID) {
                    actualSatiety = Math.max(actualSatiety + animal.weight, maxSatiety);
                    System.out.println(Data.getPicture(ID) + " ate " + Data.getPicture(foodID));

                    return true;
                }

            }

            for (Animal animal : populationList) {
                if (animal.getIDByReflection(animal) == foodID) {
                    populationList.remove(animal);
                    actualSatiety = Math.max(actualSatiety + animal.weight, maxSatiety);
                    System.out.println(Data.getPicture(ID) + " ate " + Data.getPicture(foodID));

                    return true;
                }
            }

        }

        return false;
    }

    public void move() {

    }

    public boolean reproduce() {
        boolean isReproduce = false;
        Cell cell = Island.getCell(cellID);
        List<Animal> oldPopulation = cell.populationList;
        List<Animal> newPopulation = new ArrayList<>(cell.populationList);

        for (Animal animal : oldPopulation) {

            if (ID == getIDByReflection(animal)) {

                if (!cell.isCrowded(newPopulation, animal)) {
                    Class<? extends Animal> currentAnimalType = animal.getClass();
                    newPopulation.add(createAnimalByReflection(currentAnimalType));
                    System.out.println(picture + " have it with " + animal.picture);
                    isReproduce = true;
                }
            }
        }

        cell.refreshPopulation(newPopulation);

        return isReproduce;
    }

    public void die() {
    }

    public void chooseDirection() {
    }

    public Map<Integer, Integer> getActualFoodPool() {
        Map<Integer, Integer> actualFoodPool = new HashMap<>();
        List<Animal> populationList = Island.getCell(cellID).getAnimals();

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

    public int getIDByReflection(Animal animal) {
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

    public static Animal createAnimalByReflection(Class<? extends Animal> animalType) {
        Animal animal = null;

        try {
            animal = animalType.newInstance();
        } catch (Exception e) {
            System.out.println("Вы что-нибудь видели? И я нет -_-");
        }

        return animal;
    }
}
