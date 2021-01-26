package pt.ulusofona.lp2.theWalkingDEISIGame;

public class MilitarZombie extends Zombie {
    int deslocamento = 3;
    String turno = "Todos";

    public MilitarZombie(int id, int idTipo, String nome, int x, int y, String imagemPng) {
        super(id, idTipo, nome, x, y, imagemPng);
    }
    @Override
    public String toString() {

        return id + " | " + "Militar (Zombie)" + " | " + "Os Outros" + " | " + nome + " " + getToolsDestroy() +  " @ " + salvo();
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


        return true;
    }
}
