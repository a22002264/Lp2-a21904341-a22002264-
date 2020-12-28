package pt.ulusofona.lp2.theWalkingDEISIGame;

public abstract class Zombie extends Creature {
    public int toolsDestroy;
    int totalEquipDestrui;

    public Zombie(int id, int idTipo, String nome, int x, int y, String imagemPng ) {
        super(idTipo, id, x, y, nome, imagemPng);
    }


    @Override
    public String getImagePNG() {
        return "zombie.png";
    }



    public int getTipo() {
        return idTipo;
    }




}
