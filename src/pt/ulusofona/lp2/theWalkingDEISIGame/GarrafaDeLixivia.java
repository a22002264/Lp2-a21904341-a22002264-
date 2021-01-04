package pt.ulusofona.lp2.theWalkingDEISIGame;

public class GarrafaDeLixivia extends Equipamento {
    private double litros = 1.0;
    boolean inutil = false;
    private int usos=0;

    public int getUsos(){
        return usos;
    }

    public void diminuirLitos() {
        if (litros == 0.1) {
            inutil = true;
        } else {
            litros = litros - 0.3;
            usos++;
        }
    }

    public double getLitros() {
        return litros;
    }

    public GarrafaDeLixivia(int id, int idTipo, int x, int y, String titulo, String imagemPng, String nome) {
        super(id, idTipo, x, y, titulo, imagemPng, nome);
    }
}
