package pt.ulusofona.lp2.theWalkingDEISIGame;

public class ZombieVampiro extends Zombie {

    int deslocamento = 2;

    public ZombieVampiro(int id, int idTipo, String nome, int x, int y, String imagemPng) {
        super(id, idTipo, nome, x, y, imagemPng);
    }
    @Override
    public String toString() {

        return id + " | " + "Zombie Vampiro" + " | " + "Os Outros" + " | " + nome + " " + totalEquipDestrui +  " @ " + salvo();
    }
    @Override
    public boolean comportamentos(int xD, int yD, boolean diaNoite, boolean safeHaven) {
        if (safeHaven == true) {
            return false;

        }
        if (Math.abs((xD - x)) > deslocamento) {
            return false;
        }
        if (Math.abs((yD - y)) > deslocamento) {
            return false;
        }


        if (diaNoite == true) {
            return false;
        }
        return true;
    }


}
