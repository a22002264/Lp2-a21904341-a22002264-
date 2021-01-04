package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Veneno extends Equipamento {
    private boolean usado;
    private int turnoAtivo = -1;
    int maxTurno = 3;

    public boolean getUsado(){
        return usado;
    }

    public void aumentarTurno(){
        turnoAtivo += 1;
    }

    public int getTurnoAtivo(){return turnoAtivo;}

    public void usar(){
        usado = true;
    }

    public Veneno(int id, int idTipo, int x, int y, String titulo, String imagemPng,String nome) {
        super(id, idTipo, x, y, titulo, imagemPng, nome);
    }
}
