package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Humano {


    int idTipo;
    String imagePNG;
    int x;
    int y;
    int idCriatura;
    String nomeCriatura;
    int idEquipamento;

    public Humano(int idCriatura, int idTipo, String nomeCriatura, int x, int y, int idEquipamento) {
        this.idTipo = idTipo;
        this.x = x;
        this.y = y;
        this.nomeCriatura = nomeCriatura;
        this.idCriatura = idCriatura;
        this.idEquipamento = idEquipamento;

    }


    public int getId() {

        return idCriatura;
    }


    public String getImagePNG() {


        return "human.png";
    }

    public String toString() {


        return " ";
    }

    public int getTipo() {


        return idTipo;
    }


}
