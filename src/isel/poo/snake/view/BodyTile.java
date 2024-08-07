package isel.poo.snake.view;

import isel.leic.pg.Console;
import isel.poo.snake.model.BodyCell;
import isel.poo.snake.model.HeadCell;

//Classe responsavél por imprimir a representação da BodyCell na consola
public class BodyTile extends CellTile{
    private BodyCell c;
     BodyTile(BodyCell cell) {
        c=cell;
    }

    @Override
    public void paint() {
        if(c.head instanceof HeadCell) printBox(0,0,1,1,Console.RED);
        else printBox(0, 0, 1, 1, Console.GRAY);
        Console.setForeground(Console.BLACK);
        print(0,0,c.getID());
    }
}
