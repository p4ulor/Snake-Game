package isel.poo.snake.model;

public class EmptyCell extends Cell {

    @Override
    public boolean getIsMovable() {
        return false;
    }

    @Override
    public boolean isKiller() {
        return false;
    }

    @Override
    public void step() {  }

    @Override
    public void init(Level l) {
        level=l;
    }


}
