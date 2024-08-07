package isel.poo.snake.model;
/*Class que é um enumeravél e armazena as 4 direções possiveis que um objeto pode ter
 */
public enum Dir {
    DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1), UP(-1, 0);
    //Representação cartesiana das direções
    private int dLine, dCol;

    Dir(int deltaL, int deltaC) {
        dLine = deltaL;
        dCol = deltaC;
    }
    //Indentifica qual é a direção que corresponde atráves de 2 inteiros que podem tormar os valores -1,0 ou 1
    public static Dir get(int dLine, int dCol) {
        for (Dir d : values())
            if (d.dCol == dCol && d.dLine == dLine) return d;
        return null;
    }

    public int getDLine() {
        return dLine;
    }

    public int getDCol() {
        return dCol;
    }
}
