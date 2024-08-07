package isel.poo.snake.model;

public class AppleCell extends Cell {
    //Armazena o caracter que identifica esta class
    private final char ID;

    AppleCell(char type) {
        ID =type;
    }

    //Inicia a class indica ao Level quantas Apples estão na arena
    @Override
    public void init(Level l) {
        level=l;
        level.applesInArena++;
    }
    /*Chamado quando um objeto colide com o objeto de class.
     Responsavél por remover uma Apple da arena e informar o Level.
    É adicionado uma Apple caso se verifique que ainda há Apples para serem adicionados à arena.
    Informa o objeto ,que colidio, com quem colidio .*/
    @Override
    protected void collideWith(Cell cell) {
        if(level.getRemainingApples()>level.applesInArena) createApple();
        removeApple();
        cell.updateAfterCollide(this);
    }
    //Adiciona uma Apple à arena
    private void createApple(){
        Cell apple;
        (apple= new AppleCell(ID)).position=newRandPosition();
        level.putCell(apple);
    }
    //Remove uma Apple à Arena
    private void removeApple(){
        level.removeCell(this);
        level.applesInArena--;
    }
    //Cria aleatoriamente uma nova posição aceitavél para Apple
    private Position newRandPosition() {
        int l,c;
        do{
            l=(int)(Math.random()*level.getHeight());
            c=(int)(Math.random()*level.getWidth());
        }while (level.getCell(l,c)!=null);
        return new Position(l,c);
    }

    @Override
    public boolean getIsMovable() {
        return false;
    }

    @Override
    public boolean isKiller() {
        return false;
    }

    @Override
    public char getID() {
        return ID;
    }
}
