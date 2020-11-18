package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Equipamento {

    int id;
    int idTipo;
    int x;
    int y;

    public Equipamento(int id, int idTipo, int x, int y) {

        this.id = id;
        this.idTipo = idTipo;
        this.x = x;
        this.y = y;


    }


    public int getId() {
        return this.id;
    }

    // retirar esta merda
    public int getIdTipo() {
        return this.idTipo;
    }
}
