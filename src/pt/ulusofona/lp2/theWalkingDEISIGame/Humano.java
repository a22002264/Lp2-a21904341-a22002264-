package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Humano {
    int idTipo;
    int x;
    int y;
    int idCriatura;
    String nomeCriatura;
    int totalEquipamentos;
    Equipamento equipamento;

    public Humano(int idCriatura, int idTipo, String nomeCriatura, int x, int y) {
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
        return "human.png";
    }

    public String toString() {
        return idCriatura + " | " + "Humano" + " | " + "Os Vivos" + " | " + nomeCriatura + " " + totalEquipamentos + " @ (" + x + ", " + y + ")";
    }

    public int getTipo() {
        return idTipo;
    }
}
