package isel.poo.snake.view;

import isel.leic.pg.Console;
import isel.poo.snake.model.HeadCell;
//Classe responsavél por imprimir a representação da HeadCell na consola
public class HeadTile extends CellTile {
    private HeadCell cell;

    HeadTile(HeadCell c) {
        cell=c;
    }

    @Override
    public void paint() {
        printBox(0,0,1,1, Console.YELLOW);
        Console.setForeground(Console.BLACK);
        print(0,0,cell.getID());
    }
}
