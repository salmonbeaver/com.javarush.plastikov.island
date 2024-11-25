package entity;

public abstract class Predator extends Animal {

    protected Predator(int id) {
        super(id);
    }

    @Override
    public int eat(){
        System.out.println("Затычка для предаторов");
        return 0;
    }
}
