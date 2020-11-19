package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Zombie {


    int idTipo;
    int x;
    int y;
    String imagePNG;
    int idCriatura;
    String nomeCriatura;

    public Zombie(int idCriatura, int idTipo, String nomeCriatura, int x, int y) {
        this.idTipo = idTipo;
        this.x = x;
        this.y = y;
        this.nomeCriatura = nomeCriatura;
        this.idCriatura = idCriatura;


    }

    public int getId() {

        return idCriatura;
    }


    public String getImagePNG() {


        return "zombie.png";
    }

    public String toString() {


        return " ";
    }

    public int getTipo() {

        return idTipo;
    }

}
