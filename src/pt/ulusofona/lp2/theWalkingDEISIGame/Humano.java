package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Humano {
    int x;
    int y;
    int id;
    String nome;
    int usados;
    Equipamento equipamento;

    public Humano(int id, String nome, int x, int y) {
        this.x = x;
        this.y = y;
        this.nome = nome;
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getImagePNG() {
        return "human.png";
    }

    public String toString() {
        return id + " | " + "Humano" + " | " + "Os Vivos" + " | " + nome + " " + usados + " @ (" + x + ", " + y + ")";
    }

    public void coordenadaVertical(int x) {
        this.x = x;
    }

    public void coordenadaHorizontal(int y) {
        this.y = y;
    }
    
}
