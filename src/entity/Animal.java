package entity;

import lombok.Getter;
import lombok.Setter;
import settings.*;

import java.lang.reflect.Field;
import java.util.*;

@Setter
public class Animal extends AbstractAnimal {

    private static final Random random = new Random();

    private int ID;
    @Getter private String picture;
    private double weight;
    @Getter private int speed;
    private double maxSatiety;
    private double actualSatiety;
    private Map<Integer, Integer> foodPool;
    private int cellID;

    public Animal(int id) {
        ID = id;
        picture = Data.getPictureByID(id);
        weight = Data.getWeightByID(id);
        speed = Data.getSpeedByID(id);
        maxSatiety = Data.getMaxSatietyByID(id);
        actualSatiety = maxSatiety * 0.5;
        foodPool = Data.getFoodPoolByID(id);
    }

    // работает, но с перебоями
    public boolean eat() {

        if(!isHungry()) {
            return true;
        }

        List<Integer> foodIDList = new ArrayList<>(getActualFoodPool().keySet()); // упорядоченные значения id еды
        int numberThatWillBeEaten = random.nextInt(foodIDList.size()); // случайный номер еды из пула доступной еды
        int foodID = foodIDList.get(numberThatWillBeEaten); // выбранный слуайный ID еды
        int chance = getActualFoodPool().get(foodID); // шанс соответсвующий этому ID
        boolean canEat = random.nextInt(100) < chance; // проверяем, можем ли съесть еду

        if (chance == 100) {
            Island.getCell(cellID).getPlantList().removeLast();
            System.out.println(Data.getPictureByID(getId()) + " ate gross");
            return true;
        }

        if (canEat) {
            List<Animal> populationList = Island.getCell(cellID).getPopulationList();
            Iterator iterator = populationList.iterator();

            while (iterator.hasNext()) {
                Animal animal = (Animal) iterator.next();

                if (animal.getId() == foodID) {
                    System.out.println("Satiety before breakfast is " + actualSatiety);
                    actualSatiety = Math.min(actualSatiety + animal.weight, maxSatiety);
                    System.out.println(Data.getPictureByID(getId()) + " ate " + Data.getPictureByID(foodID));
                    System.out.println("Now satiety is " + actualSatiety);

                    return true;
                }

            }

            for (Animal animal : populationList) {
                if (animal.getId() == foodID) {
                    animal.die();
                    actualSatiety = Math.max(actualSatiety + animal.weight, maxSatiety);
                    System.out.println(Data.getPictureByID(getId()) + " ate " + Data.getPictureByID(foodID));

                    return true;
                }
            }

        }

        return false;
    }

    public void move() {
        int turns = speed;

        List<Runnable> directionMethodList = new ArrayList<>();
        
        if (this.canGoLeft()) {
            directionMethodList.add(this::goLeft);
        }
        
         if (this.canGoRight()) {
            directionMethodList.add(this::goRight);
        }
        
        if (this.canGoDown()) {
            directionMethodList.add(this::goDown);
        }
        
        if (this.canGoUp()) {
            directionMethodList.add(this::goUp);
        }

        // Действительное число ходов, которое сделает животное (от 0 до макс возможного)
        int actuallyMovesCount = MyRandom.random.nextInt(turns);

        if (actuallyMovesCount == 0) {
            System.out.println("Сегодня животное ленивое");
        }

        // Выбор направления и движение в случайную клетку
        for (int i = 0; i < turns; i++) {
            int directionID = MyRandom.random.nextInt(directionMethodList.size());
            directionMethodList.get(directionID).run();
        }

    }

    public boolean reproduce() {
        boolean isReproduced = false;
        Cell cell = Island.getCell(cellID);

        if (!cell.isCrowded(cell.getPopulationList(), this)) {
            return isReproduced;
        }

        for (Animal animal : cell.getPopulationList()) {

            if (getId() == animal.getId()) {
                System.out.println(picture + " had it with " + animal.picture);
                cell.getPopulationList().add(new Animal(getId()));
                isReproduced = true;

                return isReproduced;
            }
        }

        return isReproduced;
    }

    public void die() {
        Cell cell = Island.getCell(cellID);
        cell.getPopulationList().remove(this);
    }

    public Map<Integer, Integer> getActualFoodPool() {
        Map<Integer, Integer> actualFoodPool = new HashMap<>();
        List<Animal> populationList = Island.getCell(cellID).getAnimals();

        // Делаем выборку доступной еды из находящихся на клетке живности
        for (Map.Entry<Integer, Integer> entry : foodPool.entrySet()) {

            for (Animal animal : populationList) {
                int check = animal.getId();

                if (check == entry.getKey()) {
                    actualFoodPool.put(entry.getKey(), entry.getValue());
                }

                // Все животные из пула еды в клетке
                if (foodPool.size() == actualFoodPool.size()) {
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

    public boolean isHungry() {
        return !(actualSatiety >= maxSatiety * 0.5);
    }

    private boolean canGoLeft() {
        return (cellID + 1) % (Island.getWIDTH() + 1) != 1;
    }

    private boolean canGoRight() {
        return (cellID + 1) % (Island.getWIDTH() + 1) != 0;
    }

    private boolean canGoUp() {
        return cellID > Island.getWIDTH();
    }

    private boolean canGoDown() {
        return cellID + 1 < (Island.getWIDTH() + 1) * (Island.getHEIGHT() + 1) - Island.getWIDTH() + 1;
    }

    private void goLeft() {
        int oldCellID = cellID;
        int newCellID = cellID--;
        Island.getCell(newCellID).getPopulationList().add(this);
        Island.getCell(oldCellID).getPopulationList().remove(this);
        System.out.println(Data.getPictureByID(getId()) + " походил налево");
    }

    private void goRight() {
        int oldCellID = cellID;
        int newCellID = cellID++;
        Island.getCell(newCellID).getPopulationList().add(this);
        Island.getCell(oldCellID).getPopulationList().remove(this);
        System.out.println(Data.getPictureByID(getId()) + " походил направо");
    }

    private void goUp() {
        int oldCellID = cellID;
        int newCellID = cellID + Island.getWIDTH();
        Island.getCell(newCellID).getPopulationList().add(this);
        Island.getCell(oldCellID).getPopulationList().remove(this);
        System.out.println(Data.getPictureByID(getId()) + " походил вверх");
    }

    private void goDown() {
        int oldCellID = cellID;
        int newCellID = cellID - Island.getWIDTH();
        Island.getCell(newCellID).getPopulationList().add(this);
        Island.getCell(oldCellID).getPopulationList().remove(this);
        System.out.println(Data.getPictureByID(getId()) + " походил вниз");
    }

    public int getId() {
        return ID;
    }
}
