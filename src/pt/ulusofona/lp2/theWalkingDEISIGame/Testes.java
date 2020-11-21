package pt.ulusofona.lp2.theWalkingDEISIGame;

import org.junit.Test;
import java.io.File;
import static org.junit.Assert.assertEquals;

public class Testes {
    @Test
    public void testNãoMudarPosicao(){
        TWDGameManager jogo = new TWDGameManager();
        File file = new File("Ficheiro.txt");
        jogo.startGame(file);
        boolean obtido = jogo.move(3,3,3,3);
        boolean esperado = false;
        assertEquals(esperado,obtido);
    }

    @Test
    public void testForaDoMapa(){
        TWDGameManager jogo = new TWDGameManager();
        File file = new File("Ficheiro.txt");
        jogo.startGame(file);
        boolean obtido = jogo.move(4,4,5,4);
        boolean esperado = false;
        assertEquals(esperado,obtido);
    }

    @Test
    public void testMoverDiagonal(){
        TWDGameManager jogo = new TWDGameManager();
        File file = new File("Ficheiro.txt");
        jogo.startGame(file);
        boolean obtido = jogo.move(0,0,1,1);
        boolean esperado = false;
        assertEquals(esperado,obtido);
    }

    @Test
    public void testMoverMaisQueUmaCasa(){
        TWDGameManager jogo = new TWDGameManager();
        File file = new File("Ficheiro.txt");
        jogo.startGame(file);
        boolean obtido = jogo.move(0,0,2,0);
        boolean esperado = false;
        assertEquals(esperado,obtido);
    }
}
