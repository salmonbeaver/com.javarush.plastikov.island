package entity.herbivore;

import lombok.Getter;

public class Caterpillar extends Herbivore{

    @Getter
    private static final int ID = 3;

    public Caterpillar () {
        super(ID);
    }

    @Override
    public void reduceSatiety() {
        // Этому животному не нужно насыщатся по условию
        // Если сделать ему сытость > 0, переопределенный метод удалить
    }
}
