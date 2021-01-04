package pt.ulusofona.lp2.theWalkingDEISIGame;

public class RevistaMaria extends Equipamento {
    private int dentaduras = 0;

    public void aumentarDentaduras(){
        dentaduras += 1;
    }

    public RevistaMaria(int id, int idTipo, int x, int y, String titulo, String imagemPng,String nome) {
        super(id, idTipo, x, y, titulo, imagemPng, nome);
    }
}
