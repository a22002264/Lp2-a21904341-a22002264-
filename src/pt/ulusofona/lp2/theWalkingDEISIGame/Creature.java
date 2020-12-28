package pt.ulusofona.lp2.theWalkingDEISIGame;

public abstract class Creature {
    protected int idTipo;
    protected int id;
    protected int x;
    protected int y;
    protected String nome;
    protected String imagemPng;
    protected boolean passouSafeHaven;
    protected boolean morta;

    public Creature(int idTipo, int id, int x, int y, String nome, String imagemPng) {
        this.x = x;
        this.idTipo = idTipo;
        this.id = id;
        this.nome = nome;
        this.y = y;
        this.imagemPng = imagemPng;

    }

    public boolean getPassouSafeHeaven() {
        return passouSafeHaven;
    }


    public void setPassouSafeHaven(boolean passouSafeHaven) {
        this.passouSafeHaven = passouSafeHaven;
    }


    public int getId() {
        return id;
    }

    public abstract String getImagePNG();

    public void coordenadaVertical(int x) {
        this.x = x;
    }

    public void coordenadaHorizontal(int y) {
        this.y = y;
    }

    public abstract String toString();

    public int getTipo() {
        return idTipo;
    }


    public abstract boolean comportamentos(int xD, int yD, boolean diaNoite);

    public String salvo() {
        if (passouSafeHaven == true) {
            return "A salvo";

        } else if (morta == true) {
            return "RIP";
        } else {
            return "(" + x + ", " + y + ")";
        }

    }
}
