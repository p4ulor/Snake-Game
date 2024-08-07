package isel.poo.snake.model;

public class ObstacleCell extends Cell {
    private final char ID;

    ObstacleCell(char type) {
        ID=type;
    }

    @Override
    public void init(Level l) {
        level=l;
    }

    @Override
    public boolean isKiller() { return true; }

    @Override
    public boolean getIsMovable() {
        return false;
    }

    @Override
    public char getID() {
       return ID;
    }
}
