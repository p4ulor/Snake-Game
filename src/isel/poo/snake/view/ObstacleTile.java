package isel.poo.snake.view;

import isel.leic.pg.Console;
import isel.poo.snake.model.ObstacleCell;
//Classe responsavél por imprimir a representação da ObstacleCell na consola
public class ObstacleTile extends CellTile {
    private ObstacleCell cell;


    ObstacleTile(ObstacleCell c) {
        cell=c;
    }
    @Override
    public void paint() {
        printBox(0,0,1,1, Console.BROWN);
    }
}
