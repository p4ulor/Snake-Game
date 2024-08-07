package isel.poo.snake.view;

import isel.leic.pg.Console;
import isel.poo.snake.model.AppleCell;
//Classe responsavél por imprimir a representação da AppleCell na consola
public class AppleTile extends CellTile {
    private AppleCell cell;

     AppleTile(AppleCell c) {
        cell = c;
    }

    @Override
    public void paint() {
        printBox(0,0,1,1, Console.LIGHT_GRAY);
        Console.setForeground(Console.BLACK);
        print(0,0,cell.getID());
    }
}
