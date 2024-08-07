package isel.poo.snake.view;


import isel.leic.pg.Console;
import isel.poo.snake.model.EmptyCell;
//Classe responsavél por imprimir a representação null na consola
public class EmptyTile extends CellTile {
    private EmptyCell cell;
    public EmptyTile(EmptyCell c) {
        cell=c;
    }

    public EmptyTile() {

    }
    @Override
    public void paint() {
        printBox(0,0,1,1, Console.LIGHT_GRAY);
    }
}
