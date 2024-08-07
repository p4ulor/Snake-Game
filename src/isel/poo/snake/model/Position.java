package isel.poo.snake.model;
//Classe responsável pela a localização dos objetos na arena
public final class Position {
    static int limitL;
    static int limitC;
    private int lin;
    private int col;

    public Position(int l, int c) { lin = l; col = c; }
    //Cria um novo objeto indentico ao de p
    public Position(Position p) { lin = p.lin; col = p.col; }
    //Altera a posição dependo da direção dir
     Position move(Dir dir) {
        lin += dir.getDLine();
        col += dir.getDCol();
        return this;
    }

    private static int normalizeValue(int value, int max) { return (value % max + max) % max; }

    //Normaliza a posição para estar dentro da arena
    final Position normalize() {
        lin=normalizeValue(lin,limitL);
        col=normalizeValue(col,limitC);
        return this;
    }

    int getLin() {
        return lin;
    }

    int getCol() {
        return col;
    }
    //Retorna a posição adiante
    Position getNextPosition(Dir dir) {
        return new Position(lin+dir.getDLine(),col+dir.getDCol());
    }
}

