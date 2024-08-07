
package isel.poo.snake.model;

public class BodyCell extends Cell {
    //Armazena o caracter que identifica esta class
    private final char ID ='#';
    //Informação da cabeça que os objetos desta classe têm de seguir
    public Cell head;
    //Construtor que localiza e coloca o objeto na arena indicado a cabeça
    BodyCell(Cell h) {
        init(h.level);
        head=h;
        position=new Position(h.position.getLin()-h.dir.getDLine(),h.position.getCol()-h.dir.getDCol()).normalize();
        dir=Dir.get(h.dir.getDLine(),h.dir.getDCol());
        level.putCell(this);
    }

    @Override
    public void init(Level l) {
        level=l;
    }
    //Reposiciona este objeto para estar na posição anterior à da cabeça
    @Override
    public void step() {
        level.removeCell(this);
        position=new Position(head.position.getLin()-head.dir.getDLine(),head.position.getCol()-head.dir.getDCol()).normalize();
        level.putCell(this);
    }

    @Override
    public char getID() {
        return ID;
    }

    @Override
    public boolean isKiller() { return true; }

    @Override
    public boolean getIsMovable() {
        return false;
    }
}

