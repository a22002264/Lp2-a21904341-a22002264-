package pt.ulusofona.lp2.theWalkingDEISIGame;

public class PistolaWaltherPPK extends  Equipamento {
    private int balas = 3;

    public void gastarBalas(){
        if(balas != 0){
            balas -= 1;
        }
    }

    public int getBalas(){
        return balas;
    }

    public PistolaWaltherPPK(int id, int idTipo, int x, int y, String titulo, String imagemPng,String nome) {
        super(id, idTipo, x, y, titulo, imagemPng, nome);
    }
}
