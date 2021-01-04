package pt.ulusofona.lp2.theWalkingDEISIGame;

public class  Antidoto extends Equipamento {
    private boolean usado = false;

    public boolean getUsado(){
        return usado;
    }

    public void usar(){
        usado = true;
    }

    public Antidoto(int id, int idTipo, int x, int y, String titulo, String imagemPng,String nome) {
        super(id, idTipo, x, y, titulo, imagemPng, nome);
    }
}
