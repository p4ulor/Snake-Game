package isel.poo.snake.model;

public abstract class Cell {
    //Indica o nivél atual
    Level level;
    //Indica a direção em que movimenta
    Dir dir;
    //Indica a posição
    Position position;
    //inicializa os objetos
    public abstract void init(Level l);
    //informa se é um objeto que tem movimento
    public abstract boolean getIsMovable();
    //informa se o objeto elimana o adversãrio caso haja colisão
    public abstract boolean isKiller();

    //indica o movimento que objeto deve ter
    public  void step(){}
    //informa se o objeto foi eleminado
    public boolean isDead(){ return false; }
    //atribui ao objeto a informação que foi morto
    public void setDead(boolean isDead){ }
    //Chamado quando existe uma colisão e indica a quem colidio com quem foi
    protected void collideWith(Cell cell) { }
    //Rotina que faz após colidir com um objeto
    protected void updateAfterCollide(Cell c) { }
    //Retorna o caracter que indetifica a class
    public char getID() {
        return ' ';
    }
    //Condição que indica se é deseja que seja aplicado a rotina de step após ter sido eliminado
    public boolean movableAfterDeath(){return false;}

    protected Cell() { }
    // Chamado na Class loader. Cria um objeto da classe que tem o caracter type com indetificador
    static Cell newInstance(char type){
        switch (type){
            case 'X': return new ObstacleCell(type);
            case 'A': return new AppleCell('O');
            case '@': return new HeadCell(type);
            case '*': return new NPCHeadCell(type);
        }
        return null;
    }


}
