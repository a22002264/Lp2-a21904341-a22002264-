package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Humano {
    int idTipo;
    int x;
    int y;
    int idCriatura;
    String nomeCriatura;
    int idEquipamento;
    int totalEquipamentos;

    public Humano(int idCriatura, int idTipo, String nomeCriatura, int x, int y, int idEquipamento) {
        this.idTipo = idTipo;
        this.x = x;
        this.y = y;
        this.nomeCriatura = nomeCriatura;
        this.idCriatura = idCriatura;
        this.idEquipamento = idEquipamento;

    }

    public int getId() {
        return this.idCriatura;
    }

    public String getImagePNG() {
        return "human.png";
    }

    public String toString() {

        return idCriatura + " | " + idTipo + " | " + "Os Vivos" + " | " + nomeCriatura + totalEquipamentos + " @ (" + x + ", " + y + ")";
    }

    public int getTipo() {
        return idTipo;
    }
}
