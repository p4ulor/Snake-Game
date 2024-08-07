package isel.poo.snake.view;

import isel.poo.console.tile.Tile;
import isel.poo.snake.model.*;


public abstract class CellTile extends Tile {
    public static final int SIDE =1 ;

    @Override
    public abstract void paint();

    public static Tile tileOf(Cell cell) {
        if(cell instanceof AppleCell) return new AppleTile((AppleCell) cell);
        if(cell instanceof ObstacleCell) return new ObstacleTile((ObstacleCell) cell);
        if(cell instanceof HeadCell)return new HeadTile((HeadCell) cell);
        if(cell instanceof BodyCell)return new BodyTile((BodyCell) cell);
        if(cell instanceof NPCHeadCell)return new NPCHeadTile((NPCHeadCell) cell);
    return new EmptyTile();
    }

}


