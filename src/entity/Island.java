package entity;

import java.util.ArrayList;
import java.util.List;

public class Island {

    private static final int WIDTH = 100;
    private static final int HEIGHT = 20;
    private static final int FIELD_SIZE = WIDTH * HEIGHT;
    private static final List<Cell> CELL_LIST = new ArrayList<>(FIELD_SIZE);

    public static void init() throws InstantiationException, IllegalAccessException {
        for (int i = 0; i < FIELD_SIZE; i++) {
            Cell cell = new Cell();
            cell.populate();
            CELL_LIST.add(cell);

        }
    }

    public static Cell getCell(int i) {
        return CELL_LIST.get(i);
    }

}
