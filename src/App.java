import data.Animals;
import entity.*;

import java.lang.reflect.Field;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        Settings.init();
        System.out.println(Bear.getPicture());
        System.out.println(Boar.PICTURE);
        System.out.println(Buffalo.PICTURE);
        System.out.println(Caterpillar.PICTURE);
        System.out.println(Deer.PICTURE);
        System.out.println(Duck.PICTURE);
        System.out.println(Eagle.PICTURE);
        System.out.println(Fox.PICTURE);
        System.out.println(Goat.PICTURE);
        System.out.println(Horse.PICTURE);
        System.out.println(Mouse.PICTURE);
        System.out.println(Plant.PICTURE);
        System.out.println(Rabbit.PICTURE);
        System.out.println(Sheep.PICTURE);
        System.out.println(Snake.PICTURE);
        System.out.println(Wolf.PICTURE);
        System.out.println(Bear.getPicture());

    }
}
