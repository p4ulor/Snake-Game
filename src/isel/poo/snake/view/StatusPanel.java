package isel.poo.snake.view;

import isel.poo.console.FieldView;
import isel.poo.console.ParentView;
//Classe responsavél por mostrar a informação do nivel,número de maças que faltam para o fim e os pontos do jogador
public class StatusPanel extends ParentView {
    //Dimensão das caixas informativas
    public static final int WIDTH=10;
    //dimensão da janela do jogo
    private int windowWidth;
    //Numero do nivel
    private int number;
    //Numero de Apples por comer até o fim do nivel
    private int remainingApples;
    //Numero de pontos do jogador
    private int score;
    //Inicializa as variaveis com os valores inciais do nível
    public StatusPanel(int winWidth) {
        windowWidth=winWidth;
        remainingApples=7*number;
        number=0;
        score=0;

    }
    //cria a caixa informativa que indica o numero do nível
    public void setLevel(int n) {
        number = n;
        new FieldView("Level ",1,windowWidth,""+number).paint();
    }
    //cria a caixa informativa que indica o numero de Apples restantes.
    public void setApples(int a) {
        remainingApples = a;
       new FieldView("Apples",4,windowWidth,""+remainingApples).paint();
    }
    //cria a caixa informativa que indica o numero de pontos que o jogador tem.
    public void setScore(int s) {
        score = s;
        new FieldView("Score ",7,windowWidth,score+"").paint();
    }

}
