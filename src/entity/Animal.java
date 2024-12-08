package entity;

import entity.herbivore.Herbivore;
import lombok.Getter;
import lombok.Setter;
import settings.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

@Setter
public class Animal extends AbstractAnimal {

    private int id;
    @Getter
    private String picture;
    private double weight;
    @Getter
    private int speed;
    private double maxSatiety;
    @Getter
    private double actualSatiety;
    private Map<Integer, Integer> foodPool;
    private int cellID;
    private boolean isReproduced;
    private boolean isMoved;

    public Animal(int id) {
        this.id = id;
        picture = Data.getPictureByID(id);
        weight = Data.getWeightByID(id);
        speed = Data.getSpeedByID(id);
        maxSatiety = Data.getMaxSatietyByID(id);
        actualSatiety = maxSatiety * 0.7;
        foodPool = Data.getFoodPoolByID(id);
        isReproduced = true;
        isMoved = true;
    }

    // Устраивает
    public boolean eat() {

        if (!isHungry()) {
            return false;
        }

        List<Integer> foodIDList = new ArrayList<>(getActualFoodPool().keySet()); // упорядоченные значения id еды
        int numberThatWillBeEaten = MyRandom.RANDOM.nextInt(foodIDList.size()); // случайный номер еды из пула доступной еды
        int foodID = foodIDList.get(numberThatWillBeEaten); // выбранный слуайный ID еды
        int chance = getActualFoodPool().get(foodID); // шанс соответсвующий этому ID
        boolean canEat = MyRandom.RANDOM.nextInt(100) < chance; // проверяем, можем ли съесть еду

        if (foodIDList.isEmpty()) {
            return false;
        }

        if (Island.getCell(cellID).getPlantList().isEmpty()) {
            return false;
        }

        // Если не выролил в 1 раз,то даем второй шанс травоядному, потому что траву можно есть со 100% вероятностью
        if (this instanceof Herbivore && !canEat) {
            chance = 100;
        }

        // Кушаем травку
        if (chance == 100) {
            Plant.die(cellID);
            actualSatiety = Math.min(actualSatiety + Plant.getWeight(), maxSatiety);

            return true;
        }

        // Кушаем животное
        if (canEat) {
            ConcurrentLinkedQueue<Animal> populationQueue = Island.getCell(cellID).getPopulationQueue();

            for (Animal animal : populationQueue) {

                if (animal.getId() == foodID) {
                    populationQueue.remove(); // смерть
                    actualSatiety = Math.min(actualSatiety + animal.weight, maxSatiety);

                    return true;
                }

            }

        }

        return false;
    }

    // Устраивает
    public void move() {

        // Уже делал ходы или скорость = 0
        if (isMoved || speed == 0) {
            return;
        }

        // Действительное число ходов, которое сделает животное (0 - speed)
        int actualMovesCount = MyRandom.RANDOM.nextInt(speed + 1);

        // Выпало 0 ходов в этот день (решил зачилить)
        if (actualMovesCount == 0) {
            isMoved = true;
            return;
        }

        // Выпало 1+ ходов в этот день
        for (int i = 0; i < actualMovesCount; i++) {
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

            // Выбор направления
            int directionID = MyRandom.RANDOM.nextInt(directionMethodList.size());

            //  Движение в случайную клетку
            directionMethodList.get(directionID).run();

        }

        isMoved = true;
    }

    // Устраивает
    public void reproduce() {

        if (this.isReproduced) {
            return;
        }

        Cell cell = Island.getCell(cellID);

        if (cell.isCrowded(this)) {
            return;
        }

        for (Animal animal : cell.getPopulationQueue()) {

            if (!this.equals(animal)
                    && id == animal.getId()
                    && !animal.isReproduced) {

                Animal baby = createAnimalByReflection(animal.getClass());

                cell.getPopulationQueue().add(baby);

                this.isReproduced = true;
                animal.isReproduced = true;

                return;
            }
        }
    }

    public Map<Integer, Integer> getActualFoodPool() {
        Map<Integer, Integer> actualFoodPool = new HashMap<>();
        List<Integer> populationList = Island.getCell(cellID).getAnimals();

        // Делаем выборку доступной еды из находящихся на клетке живности
        for (Map.Entry<Integer, Integer> entry : foodPool.entrySet()) {

            for (int animalId : populationList) {
                int foodID = entry.getKey();

                if (foodID == animalId || foodID == Plant.getID()) {
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

    public static Animal createAnimalByReflection(Class<? extends Animal> animalType) {
        Animal animal;

        try {
            Constructor<?> animalConstructor = animalType.getConstructor();
            animal = (Animal) animalConstructor.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return animal;
    }

    public boolean isHungry() {
        return actualSatiety <= maxSatiety * 0.7;
    }

    public void reduceSatiety() {
        actualSatiety = actualSatiety - maxSatiety * 0.1;
    }

    private boolean canGoLeft() {
        return (cellID + 1) % (Island.WIDTH) != 1;
    }

    private boolean canGoRight() {
        return (cellID + 1) % (Island.WIDTH) != 0;
    }

    private boolean canGoUp() {
        return cellID > Island.WIDTH;
    }

    private boolean canGoDown() {
        return cellID + 1 < (Island.WIDTH) * (Island.HEIGHT) - Island.WIDTH;
    }

    private void goLeft() {
        int oldCellID = cellID;
        int newCellID = cellID - 1;
        Cell vacantCell = Island.getCell(newCellID);

        if (vacantCell.isCrowded(this)) {
            return;
        }

        Island.getCell(newCellID).getPopulationQueue().add(this);
        Island.getCell(oldCellID).getPopulationQueue().remove(this);

        cellID = newCellID;
    }

    private void goRight() {
        int oldCellID = cellID;
        int newCellID = cellID + 1;
        Cell vacantCell = Island.getCell(newCellID);

        if (vacantCell.isCrowded(this)) {
            return;
        }

        Island.getCell(newCellID).getPopulationQueue().add(this);
        Island.getCell(oldCellID).getPopulationQueue().remove(this);

        cellID = newCellID;
    }

    private void goUp() {
        int oldCellID = cellID;
        int newCellID = cellID - Island.WIDTH;
        Cell vacantCell = Island.getCell(newCellID);

        if (vacantCell.isCrowded(this)) {
            return;
        }

        Island.getCell(newCellID).getPopulationQueue().add(this);
        Island.getCell(oldCellID).getPopulationQueue().remove(this);

        cellID = newCellID;
    }

    private void goDown() {
        int oldCellID = cellID;
        int newCellID = cellID + Island.WIDTH;
        Cell vacantCell = Island.getCell(newCellID);

        if (vacantCell.isCrowded(this)) {
            return;
        }

        Island.getCell(newCellID).getPopulationQueue().add(this);
        Island.getCell(oldCellID).getPopulationQueue().remove(this);

        cellID = newCellID;
    }

    public void becomeFreshAgain() {
        isReproduced = false;
        isMoved = false;
    }

    public int getId() {
        return id;
    }
}
