package isel.poo.snake.view;

import isel.leic.pg.Console;
import isel.poo.snake.model.NPCHeadCell;
//Classe responsavél por imprimir a representação da NPCHeadCell na consola
public class NPCHeadTile extends CellTile {
    private NPCHeadCell cell;

    NPCHeadTile(NPCHeadCell c) { cell=c; }

    @Override
    public void paint() {
        printBox(0,0,1,1, Console.GRAY);
        Console.setForeground(Console.BLACK);
        print(0,0,cell.getID());
    }
}
