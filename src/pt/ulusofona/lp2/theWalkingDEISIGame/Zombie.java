package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Zombie {
    int id;
    String nome;
    int x;
    int y;
    String imagePNG;
    int destroy;

    public Zombie(int id, String nomeCriatura, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.nome = nome;
    }

    public int getId() {
        return this.id;
    }

    public String getImagePNG() {
        return "zombie.png";
    }

    public String toString() {
        return "0" + " | " + "Zombie" + " | " + "Os Outros" + " | " + nome + " " + destroy + " @ (" + x + "," + y + ")";
    }

}
