package pt.ulusofona.lp2.theWalkingDEISIGame;

public class EscudoTactico extends Equipamento {
    private int hitsRecebido = 0;

    public void aumentarHits(){
        hitsRecebido += 1;
    }

    public EscudoTactico(int id, int idTipo, int x, int y, String titulo, String imagemPng,String nome) {
        super(id, idTipo, x, y, titulo, imagemPng, nome);
    }
}
