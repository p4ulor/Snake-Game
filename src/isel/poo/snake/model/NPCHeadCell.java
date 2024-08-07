package isel.poo.snake.model;

import java.util.LinkedList;
//Classe responsável por criar cobras que se movimentam sozinhas.
public class NPCHeadCell extends Cell {
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
    NPCHeadCell(char type) {
        ID =type;
        stepsUntilPenalty = 10;
    }

    //inicializa o objeto criando o corpo da cobra
    @Override
    public void init(Level l) {
        level=l;
        dir=Dir.UP;
        tail=new LinkedList<>();
    }
    /*Rotina que verifica como atuar perante a casa que se encontra à frente da cobra caso não tenha sido eliminado.
    Após eliminação continua atuar a penalização perante o a cobra morta*/
    @Override
    public void step() {
        ++nSteps;
        checkPenalty();
        if(!isDead()) {
            Cell c = level.getCell(position.getNextPosition(dir).normalize());
            if (c == null) updateBody();
            else if (c.isKiller())
                if(differentPath()) updateBody();
                else setDead(true);
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
        if (numberBodyToAdd == 0) moveBody();
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
        tail.removeLast();
        tail.addFirst(b);
    }

    private void createNewBody() {
        tail.addFirst(new BodyCell(this));
        numberBodyToAdd--;
    }
    //Retorna uma direção segura para seguir.Caso não encontre a cobra é eliminada
    private boolean differentPath() {
        for(Dir d:Dir.values()) {
            Cell c = level.getCell(position.getNextPosition(dir = d).normalize());
            if (c == null || !c.isKiller()) return true;
        }
        return false;
    }
    /*Chamado quando um objeto colide com o objeto de class.
    Responsavél por remover a cabeça da cobra da arena caso esta esteja morta.
   Informa o objeto ,que colidio, com quem colidio.*/
    @Override
    protected void collideWith(Cell cell) {
        if(isDead()){
            level.removeCell(this);
            level.updateCell(this);
            cell.updateAfterCollide(this);
        }
    }
    //Rotina que indetifica com quem colidio e o que fazer após colisão
    //Só ocorre quando o objeto colididio ter isKiller() a falso
    @Override
    protected void updateAfterCollide(Cell c) {
        if(c instanceof AppleCell){
            numberBodyToAdd+=4;
            level.updateApples();
        }
        else if(c instanceof NPCHeadCell)
            numberBodyToAdd=10+2*((NPCHeadCell) c).getTailSize();
        updateBody();
    }
    //Remove a ultima parte do corpo e verifica se ainda tem corpo caso isso não ocorra a cobra é eliminada
    private void removeBody() {
        level.removeCell(tail.getLast());
        tail.removeLast();
        if(tail.size()==0)setDead(true);

    }
    //Retorna a dimensão do corpo
    int getTailSize() {
        return tail.size();
    }

    @Override
    public boolean isKiller() {
        return !isDead();
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


    public char getID() {
        return ID;
    }

    @Override
    public boolean movableAfterDeath() {
        return true;
    }
}
