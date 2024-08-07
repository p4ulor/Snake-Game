package isel.poo.snake.model;

import java.util.LinkedList;

public class HeadCell extends Cell {
    private final char ID;
    //indetificador se a cobra foi eliminada
    private boolean dead;
    //Representa o corpo da cobra
    private LinkedList<BodyCell> tail;
    //Indica quantas partes do corpo são necessária colocar
    private int numberBodyToAdd=4;
    //indica o numero de vezes o metodo step tem de ser chamado até haver uma penalização
    private int stepsUntilPenalty;
    //conta o numero de vezes que foi chamado o metodo
    private int nSteps=0;
    //Construtor que atribui o caracter indetificardor e o numero de chamadas do step até uma penalização
    HeadCell(char type) {
        ID =type;
        stepsUntilPenalty = 10;
    }
    //Indica ao nível que está é a cobra que o jogador comanda e coloca um corpo na cobra
    @Override
    public void init(Level l) {
        level=l;
        level.setSnake(this);
        tail=new LinkedList<>();
    }
    /*Rotina que verifica como atuar perante a casa que se encontra à frente da cobra caso não tenha sido eliminado.
    Após eliminação o jogo acaba*/
    @Override
    public void step() {
        ++nSteps;
        checkPenalty();
        if (!isDead()) {
            Cell c = level.getCell(position.getNextPosition(dir).normalize());
            if (c == null) updateBody();
            else if (c.isKiller()) setDead(true);
            else c.collideWith(this);
        }
    }
    //Verifica e aplica a penalidade
    private void checkPenalty() {
        if (isPenalty() && tail.size() > 0) {
            removeBody();
            level.addPoints(-1);
            nSteps = 0;
        }
    }

    //Verifica se é momento de penalizar
    private boolean isPenalty() { return (stepsUntilPenalty-nSteps)==0; }

    // Responsavél pela a atualização da posição da cobra e do seu corpo
    private void updateBody(){
        moveHead();
        if (numberBodyToAdd == 0)
            moveBody();
        else createNewBody();
    }
    //Movimentação da cabeça dependendo na direção que tem
    private void moveHead(){
        Position p=new Position(position);
        position.move(dir).normalize();
        level.moveCell(p,this);
    }
    //Movimentação do corpo.
    //Coloca o ultima parte da cobra na posição anterior à cabeça e remove a ultima parte.
    private void moveBody() {
        BodyCell b = tail.getLast();
        b.step();
        tail.addFirst(b);
        tail.removeLast();
    }
    //Remove a ultima parte do corpo e verifica se ainda tem corpo caso isso não ocorra a cobra é eliminada
    private void removeBody() {
        level.removeCell(tail.getLast());
        tail.removeLast();
        if(tail.size()==0)setDead(true);
    }
    //Cria e indica que uma nova parte do corpo foi colocada antes da cabeça da cobra.
    private void createNewBody() {
        tail.addFirst(new BodyCell(this));
        numberBodyToAdd--;
    }
    //Rotina que indetifica com quem colidio e o que fazer após colisão
    //Só ocorre quando o objeto colididio ter isKiller() a falso
    @Override
    protected void updateAfterCollide(Cell c) {
        if(c instanceof AppleCell){
            numberBodyToAdd+=4;
            level.addPoints(4);
            level.updateApples();
        }
        else if(c instanceof NPCHeadCell) {
            numberBodyToAdd=10+2*((NPCHeadCell) c).getTailSize();
            level.addPoints(10+2*((NPCHeadCell) c).getTailSize());
        }
        updateBody();
    }


    @Override
    public boolean isKiller() {
        return true;
    }
    @Override
    public boolean isDead() {
        return dead;
    }
    public void setDead(boolean isDead) {
        dead = isDead;
    }
    @Override
    public boolean getIsMovable() {
        return true;
    }
    @Override
    public char getID() {
        return ID;
    }
}
