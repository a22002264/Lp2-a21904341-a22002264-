package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Humano {

    int id;
    String imagePNG;
    int x;
    int y;
    String nome;
    int idEquipamento;
    int armas;

    public Humano(int id, String nomeCriatura, int x, int y, int idEquipamento) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.nome = nome;
        this.idEquipamento = idEquipamento;

    }

    public int getId() {
        return this.id;
    }

    public String getImagePNG() {
        return "human.png";
    }

    public String toString() {
        return "1" + " | " + "Humano" + " | " + "Os Vivos" + " | " + nome + " " + armas + " @ (" + x + "," + y + ")";
    }

}
