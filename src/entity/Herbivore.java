package entity;

public abstract class Herbivore extends Animal{

    protected Herbivore(int id) {
        super(id);
    }

    @Override
    public int eat(){
        System.out.println("Затычка для хербиворов");
        return 0;
    }
}
