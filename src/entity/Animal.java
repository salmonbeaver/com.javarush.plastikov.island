package entity;

import lombok.Getter;
import lombok.Setter;
import settings.*;

import java.util.*;

@Setter
public class Animal extends AbstractAnimal {

    private static final Random random = new Random();

    private int ID;
    @Getter private String picture;
    private double weight;
    @Getter private int speed;
    private double maxSatiety;
    @Getter private double actualSatiety;
    public Map<Integer, Integer> foodPool;
    private int cellID;
    public boolean isTired = false;

    public Animal(int id) {
        ID = id;
        picture = Data.getPictureByID(id);
        weight = Data.getWeightByID(id);
        speed = Data.getSpeedByID(id);
        maxSatiety = Data.getMaxSatietyByID(id);
        actualSatiety = maxSatiety * 0.1;
        foodPool = Data.getFoodPoolByID(id);
    }

    public boolean eat() {

        if(!isHungry()) {
            return true;
        }

        List<Integer> foodIDList = new ArrayList<>(getActualFoodPool().keySet()); // упорядоченные значения id еды
        int numberThatWillBeEaten = random.nextInt(foodIDList.size()); // случайный номер еды из пула доступной еды
        int foodID = foodIDList.get(numberThatWillBeEaten); // выбранный слуайный ID еды
        int chance = getActualFoodPool().get(foodID); // шанс соответсвующий этому ID
        boolean canEat = random.nextInt(100) < chance; // проверяем, можем ли съесть еду

        if (foodIDList.isEmpty()) {
            return false;
        }

        if (Island.getCell(cellID).getPlantList().isEmpty()) {
            return false;
        }

        // Кушаем травку
        if (chance == 100) {
            Plant.die(cellID);
            actualSatiety = Math.min(actualSatiety + Plant.getWeight(), maxSatiety);

            return true;
        }

        // Кушаем животное
        if (canEat) {
            List<Animal> populationList = Island.getCell(cellID).getPopulationList();

            for (Animal animal : populationList) {

                if (animal.getId() == foodID) {
                    animal.die();
                    actualSatiety = Math.min(actualSatiety + animal.weight, maxSatiety);

                    return true;
                }

            }
        }

        return false;
    }

    public void move() {

        if (speed == 0) {
            isTired = true;

            return;
        }

        // Действительное число ходов, которое сделает животное (от 0 до макс возможного)
        int actualMovesCount = MyRandom.RANDOM.nextInt(speed + 1);

        // Выпало 0 ходов в этот день
        if (actualMovesCount == 0) {
            isTired = true;

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

        isTired = true;
    }

    public void reproduce() {
        Cell cell = Island.getCell(cellID);

        if (cell.isCrowded(this)) {
            return;
        }

        for (Animal animal : cell.getPopulationList()) {

            if (getId() == animal.getId()) {
                cell.getPopulationList().add(new Animal(getId()));

                return;
            }
        }
    }

    public void die() {
        Cell cell = Island.getCell(cellID);
        cell.getPopulationList().remove(this);
    }

    public Map<Integer, Integer> getActualFoodPool() {
        Map<Integer, Integer> actualFoodPool = new HashMap<>();
        List<Integer> populationList = Island.getCell(cellID).getAnimals();

        // Делаем выборку доступной еды из находящихся на клетке живности
        for (Map.Entry<Integer, Integer> entry : foodPool.entrySet()) {

            for (int id : populationList) {
                int foodID = entry.getKey();

                if (foodID == id || foodID == Plant.getID()) {
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

    public void reduceSatiety() {
        actualSatiety = actualSatiety - maxSatiety / 7;
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

        if(vacantCell.isCrowded(this)) {
            return;
        }

        Island.getCell(newCellID).getPopulationList().add(this);
        Island.getCell(oldCellID).getPopulationList().remove(this);

        cellID = newCellID;
    }

    private void goRight() {
        int oldCellID = cellID;
        int newCellID = cellID + 1;
        Cell vacantCell = Island.getCell(newCellID);

        if(vacantCell.isCrowded(this)) {
            return;
        }

        Island.getCell(newCellID).getPopulationList().add(this);
        Island.getCell(oldCellID).getPopulationList().remove(this);

        cellID = newCellID;
    }

    private void goUp() {
        int oldCellID = cellID;
        int newCellID = cellID - Island.WIDTH;
        Cell vacantCell = Island.getCell(newCellID);

        if(vacantCell.isCrowded(this)) {
            return;
        }

        Island.getCell(newCellID).getPopulationList().add(this);
        Island.getCell(oldCellID).getPopulationList().remove(this);

        cellID = newCellID;
    }

    private void goDown() {
        int oldCellID = cellID;
        int newCellID = cellID + Island.WIDTH;
        Cell vacantCell = Island.getCell(newCellID);

        if(vacantCell.isCrowded(this)) {
            return;
        }

        Island.getCell(newCellID).getPopulationList().add(this);
        Island.getCell(oldCellID).getPopulationList().remove(this);

        cellID = newCellID;
    }

    public int getId() {
        return ID;
    }
}
