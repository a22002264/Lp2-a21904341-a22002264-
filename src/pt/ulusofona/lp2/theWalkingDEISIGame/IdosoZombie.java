package pt.ulusofona.lp2.theWalkingDEISIGame;

public class IdosoZombie extends Zombie{
    int deslocamento = 1;

    public IdosoZombie(int id, int idTipo, String nome, int x, int y, String imagemPng) {
        super(id, idTipo, nome, x, y, imagemPng);
    }

    @Override
    public String toString() {

        return id + " | " + "Idoso (Zombie)" + " | " + "Os Outros" + " | " + nome + " " + getToolsDestroy() +  " @ " + salvo();
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

        if (x != xD && y != yD) {
            return false;
        }



        return true;
    }
}
