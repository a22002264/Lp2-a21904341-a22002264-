package pt.ulusofona.lp2.theWalkingDEISIGame;
public class Zombie {
    int idTipo;
    int x;
    int y;
    int idCriatura;
    String nomeCriatura;
    int totalEquipDestrui;
    public Zombie(int idCriatura, int idTipo, String nomeCriatura, int x, int y) {
        this.idTipo = idTipo;
        this.x = x;
        this.y = y;
        this.nomeCriatura = nomeCriatura;
        this.idCriatura = idCriatura;
    }
    public int getId() {
        return this.idCriatura;
    }
    public String getImagePNG() {
        return "zombie.png";
    }
    public String toString() {

        return idCriatura + " | " + "Zombie" +" | " + "Os Outros" + " | " + nomeCriatura + totalEquipDestrui + " @ (" + x + ", " + y + ")";
    }
    public int getTipo() {
        return idTipo;
    }
}
