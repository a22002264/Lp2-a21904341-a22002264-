package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Zombie {
    int x;
    int y;
    int id;
    String nome;
    int toolsDestroy;

    public Zombie(int id, String nome, int x, int y) {
        this.x = x;
        this.y = y;
        this.nome = nome;
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getImagePNG() {
        return "zombie.png";
    }

    public String toString() {

        return id + " | " + "Zombie" + " | " + "Os Outros" + " | " +nome+ " " +toolsDestroy+ " @ (" +x+ ", " + y + ")";
    }
}
