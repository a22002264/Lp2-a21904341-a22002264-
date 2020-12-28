package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Equipamento {
    protected int id;
    protected int idTipo;
    protected int x;
    protected int y;
    protected String titulo;
    protected String imagemPng;

    public Equipamento(int id, int idTipo, int x, int y, String titulo, String imagemPng) {
        this.id = id;
        this.idTipo = idTipo;
        this.x = x;
        this.y = y;
        this.titulo = titulo;
        this.imagemPng = imagemPng;
    }

    public int getId() {
        return this.id;
    }

    public int getIdTipo() {
        return this.idTipo;
    }

    public void coordenadaVertical(int x) {
        this.x = x;
    }

    public void coordenadaHorizontal(int y) {
        this.y = y;
    }

}
