package isel.poo.snake.model;

import java.util.Iterator;
import java.util.LinkedList;
//Classe que cria os niveis
public class Level {
    //numero do nivel
    private int level;
    //Numero de Apples que têm de ser comidas para acabar o nivél
    private int nApples;
    //Número de Apples comidas
    private int applesEaten = 0;
    //Número de Apples na arena
    int applesInArena = 0;
    //Arena: Onde o jogo decorre. Armazena os objetos presentes no nível
    private Cell[][] arena;
    //Responsável pela a interação com a parte visual
    private Observer observer;
    //Armazena o objeto que vai ser controlado pelo o jogador
    private Cell snake;
    //Lista que amrazena os objetos que se mexem na arena
    private LinkedList<Cell> movables;
    //Refência à classe Game. Utilizado para atualizar scores
    private Game game;

    //Cria um novo nivel inicializando a arena a lista movables e atribuindo os limites do nível
    Level(int levelNumber, int height, int width) {
        level = levelNumber;
        arena = new Cell[height][width];
        movables = new LinkedList<>();
        Position.limitL = height;
        Position.limitC = width;
    }

    //Inicia as variaveis do jogo
    void init(Game game) {
        this.game = game;
        nApples = 10 * level;
        snake.dir = Dir.UP;
    }
    //Retorna a altura da arena
    public int getHeight() {
        return arena.length;
    }
    //Retorna a largura da arena
    public int getWidth() {
        return arena[0].length;
    }
    //Retorna o número do nível
    public int getNumber() { return level; }
    //Retorna quantas Apples faltam ser consomidas para o nível acabar
    public int getRemainingApples() {
        return nApples - applesEaten;
    }
    //Retorna o objeto na posição da arena indicada em l,c
    public Cell getCell(int l, int c) {
        return arena[l][c];
    }
    //Retorna o objeto na posição da arena indicada em p
    Cell getCell(Position p) {
        return getCell(p.getLin(), p.getCol());
    }
    //Define qual é o listener do jogo
    public void setObserver(Observer updater) {
        observer = updater;
    }
    //Indica se a cobra controla pelo jogador foi eleminada
    public boolean snakeIsDead() {
        return snake.isDead();
    }
    //Altera a direção da cobra controlada pelo jogador
    public void setSnakeDirection(Dir d) { snake.dir = d; }
    //indica que o nível acabou
    public boolean isFinished() { return getRemainingApples() == 0 || snake.isDead(); }
    //Defone que é o objeto que vai ser controlado pelo jogador
    public void setSnake(Cell snake) { this.snake = snake; }
    //Coloca uma objeto na arena e indica para pintar na parte visual do jogo
    void putCell(Cell cell) {
        putCell(cell.position.getLin(), cell.position.getCol(), cell);
    }
    //Coloca uma objeto na arena na posição indica em l,c e indica para pintar na parte visual do jogo
    void putCell(int l, int c, Cell cell) {
        assert arena[l][c] == null;
        cell.init(this);
        cell.position = new Position(l, c);
        arena[l][c] = cell;
        if (cell.getIsMovable())
            movables.add(cell);
        if (observer != null) observer.cellCreated(l, c, cell);
    }
    //Responsavél por movimentar os objetos movíveis
    public void step() {
        Iterator<Cell> iterator = movables.iterator();
        Cell remove=null;
        while (iterator.hasNext()) {
            Cell c = iterator.next();
            if(remove!=null) movables.remove(remove);
            if (!c.isDead() || c.movableAfterDeath()) c.step();
            else remove=c;

        }
    }
    //Move um objeto na arena desda da posição anterior até à posição atual do objeto
    void moveCell(Position from, Cell c) {
        moveCell(from.getLin(),from.getCol(),c);

    }
    //Move um objeto na arena desda da posição anterior,indica em l e c, até à posição atual do objeto
    private void moveCell(int lin, int col, Cell c) {
        arena[lin][col] = null;
        arena[c.position.getLin()][c.position.getCol()] = c;
        if (observer != null)
            observer.cellMoved(lin,col, c.position.getLin(), c.position.getCol(), c);
    }

    void removeCell(Cell cell) {
        arena[cell.position.getLin()][cell.position.getCol()] = null;
        if (observer != null) observer.cellRemoved(cell.position.getLin(), cell.position.getCol());
    }
    //Atualiza a quantidade de Apples consumidas e pede para alterar vizualmente
    void updateApples() {
        ++applesEaten;
        observer.applesUpdated(getRemainingApples());
    }
    //Adiciona aos pontos o valor passado em points
    void addPoints(int points) {
        if(game.getScore()+points>=0)game.addScore(points);
    }
    //Pede para repintar uma objeto
    void updateCell(Cell c) {
        observer.cellUpdated(c.position.getLin(),c.position.getCol(),c);
    }

    //interface responsável pela a interação com a parte visual
    public interface Observer {
        void cellUpdated(int l, int c, Cell cell);

        void cellCreated(int l, int c, Cell cell);

        void cellRemoved(int l, int c);

        void cellMoved(int fromL, int fromC, int toL, int toC, Cell cell);

        void applesUpdated(int apples);
    }
}

